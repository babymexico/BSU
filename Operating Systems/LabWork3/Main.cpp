#include <iostream>
#include <Windows.h>
#include <cstdlib>
#include <ctime>
#include <vector>

struct ThreadInfo {
    int* arr;
    int array_size;
    int thread_index;
    HANDLE start_work;
    HANDLE stop_work;
    bool end_thread;
    HANDLE* terminate_or_continue;
};

CRITICAL_SECTION cs;

void set_zeros(int* arr, int size, int thread_index) {
    for (int i = 0; i < size; ++i) {
        if (arr[i] == thread_index) { arr[i] = 0; }
    }
}

void mark_element(ThreadInfo& info, int& number_of_marked_elements) {
    int ind = rand() % info.array_size;
    EnterCriticalSection(&cs);
    if (info.arr[ind] == 0) {
        Sleep(5);
        info.arr[ind] = info.thread_index;
        LeaveCriticalSection(&cs);
        number_of_marked_elements++;
        Sleep(5);
    }
    else {
        std::cout << "\nThread " << info.thread_index << ", number of marked elements: " << number_of_marked_elements << ", can't mark element with index " << ind;
        LeaveCriticalSection(&cs);
        SetEvent(info.stop_work);
        int k = WaitForMultipleObjects(2, info.terminate_or_continue, FALSE, INFINITE) - WAIT_OBJECT_0;
        if (k == 0) {
            info.end_thread = true;
        }
    }
}

DWORD WINAPI thread_func(LPVOID params) {
    ThreadInfo& info = *((ThreadInfo*)params);
    int number_of_marked_elements = 0;
    srand(info.thread_index);

    WaitForSingleObject(info.start_work, INFINITE);

    while (!info.end_thread) {
        mark_element(info, number_of_marked_elements);
    }
    set_zeros(info.arr, info.array_size, info.thread_index);
    return 0;
}

void display_array(const int* arr, int size) {
    std::cout << "Array: ";
    for (int i = 0; i < size; ++i) {
        std::cout << arr[i] << " ";
    }
    std::cout << "\n";
}

void terminate_thread(std::vector<bool>& terminated_threads, std::vector<ThreadInfo>& information, std::vector<HANDLE>& threads, std::vector<HANDLE>& stopped_threads, int& ended_threads) {
    bool is_thread_terminated = false;
    while (!is_thread_terminated) {
        int thread_to_terminate_ind;
        std::cout << "Enter index of thread to be terminated (starts with 1): ";
        std::cin >> thread_to_terminate_ind;
        thread_to_terminate_ind--;

        if (thread_to_terminate_ind >= information.size() || thread_to_terminate_ind < 0) {
            std::cout << "No thread with such index\n";
            continue;
        }

        if (terminated_threads[thread_to_terminate_ind]) {
            std::cout << "Thread is already terminated\n";
        }
        else {
            SetEvent(information[thread_to_terminate_ind].terminate_or_continue[0]);
            WaitForSingleObject(threads[thread_to_terminate_ind], INFINITE);
            display_array(information[thread_to_terminate_ind].arr, information[thread_to_terminate_ind].array_size);
            terminated_threads[thread_to_terminate_ind] = true;
            is_thread_terminated = true;
            ended_threads++;
        }
    }

    for (int j = 0; j < information.size(); ++j) {
        if (!terminated_threads[j]) {
            ResetEvent(information[j].stop_work);
            SetEvent(information[j].terminate_or_continue[1]);
        }
    }
}

void initialize_array(int* arr, int size) {
    for (int i = 0; i < size; ++i) {
        arr[i] = 0;
    }
}

int main() {
    InitializeCriticalSection(&cs);

    int size;
    std::cout << "Enter array size: ";
    std::cin >> size;
    int* arr = new int[size];
    initialize_array(arr, size);

    int number_of_threads;
    std::cout << "Enter number of threads: ";
    std::cin >> number_of_threads;

    std::vector<HANDLE> threads(number_of_threads);
    std::vector<ThreadInfo> information(number_of_threads);
    std::vector<bool> terminated_threads(number_of_threads, false);
    HANDLE start_work = CreateEvent(NULL, TRUE, FALSE, NULL);
    std::vector<HANDLE> stopped_threads(number_of_threads);

    for (int i = 0; i < number_of_threads; ++i) {
        information[i].arr = arr;
        information[i].array_size = size;
        information[i].thread_index = i + 1;
        information[i].start_work = start_work;
        stopped_threads[i] = information[i].stop_work = CreateEvent(NULL, TRUE, FALSE, NULL);
        information[i].terminate_or_continue = new HANDLE[2];
        information[i].terminate_or_continue[0] = CreateEvent(NULL, FALSE, FALSE, NULL);
        information[i].terminate_or_continue[1] = CreateEvent(NULL, FALSE, FALSE, NULL);
        threads[i] = CreateThread(NULL, 0, thread_func, (LPVOID)(&information[i]), NULL, NULL);
    }

    SetEvent(start_work);

    int ended_threads = 0;

    while (ended_threads != number_of_threads) {
        WaitForMultipleObjects(number_of_threads, stopped_threads.data(), TRUE, INFINITE);
        std::cout << "\n";
        display_array(arr, size);
        terminate_thread(terminated_threads, information, threads, stopped_threads, ended_threads);
    }

    std::cout << "All threads are terminated\n";

    CloseHandle(start_work);
    for (int i = 0; i < number_of_threads; ++i) {
        CloseHandle(threads[i]);
        CloseHandle(stopped_threads[i]);
        CloseHandle(information[i].terminate_or_continue[0]);
        CloseHandle(information[i].terminate_or_continue[1]);
        delete[] information[i].terminate_or_continue;
    }

    delete[] arr;
    DeleteCriticalSection(&cs);

    return 0;
}
