#include <iostream>
#include <boost/thread.hpp>
#include <boost/chrono.hpp>
#include <boost/bind.hpp>
#include <vector>

struct ThreadInfo {
    int* arr;
    int array_size;
    int thread_index;
    boost::condition_variable start_work;
    boost::condition_variable stop_work;
    bool end_thread;
    boost::mutex mtx;
    HANDLE* terminate_or_continue;
};

void set_zeros(int* arr, int size, int thread_index) {
    for (int i = 0; i < size; ++i) {
        if (arr[i] == thread_index) { arr[i] = 0; }
    }
}

void mark_element(ThreadInfo& info, int& number_of_marked_elements) {
    int ind = rand() % info.array_size;
    boost::unique_lock<boost::mutex> lock(info.mtx);
    if (info.arr[ind] == 0) {
        boost::this_thread::sleep_for(boost::chrono::milliseconds(5));
        info.arr[ind] = info.thread_index;
        lock.unlock();
        number_of_marked_elements++;
        boost::this_thread::sleep_for(boost::chrono::milliseconds(5));
    }
    else {
        std::cout << "\nThread " << info.thread_index << ", number of marked elements: " << number_of_marked_elements << ", can't mark element with index " << ind;
        lock.unlock();
        info.stop_work.notify_all();
        int k = WaitForMultipleObjects(2, info.terminate_or_continue, FALSE, INFINITE) - WAIT_OBJECT_0;
        if (k == 0) {
            info.end_thread = true;
        }
    }
}

void thread_func(ThreadInfo& info) {
    int number_of_marked_elements = 0;
    srand(info.thread_index);

    boost::unique_lock<boost::mutex> lock(info.mtx);
    info.start_work.wait(lock);

    while (!info.end_thread) {
        mark_element(info, number_of_marked_elements);
    }
    set_zeros(info.arr, info.array_size, info.thread_index);
}

void display_array(const int* arr, int size) {
    std::cout << "Array: ";
    for (int i = 0; i < size; ++i) {
        std::cout << arr[i] << " ";
    }
    std::cout << "\n";
}

void terminate_thread(std::vector<bool>& terminated_threads, std::vector<ThreadInfo>& information, std::vector<boost::thread>& threads, int& ended_threads) {
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
            information[thread_to_terminate_ind].stop_work.notify_all();
            threads[thread_to_terminate_ind].join();
            display_array(information[thread_to_terminate_ind].arr, information[thread_to_terminate_ind].array_size);
            terminated_threads[thread_to_terminate_ind] = true;
            is_thread_terminated = true;
            ended_threads++;
        }
    }

    for (int j = 0; j < information.size(); ++j) {
        if (!terminated_threads[j]) {
            information[j].stop_work.notify_all();
        }
    }
}

void initialize_array(int* arr, int size) {
    for (int i = 0; i < size; ++i) {
        arr[i] = 0;
    }
}

int main() {
    int size;
    std::cout << "Enter array size: ";
    std::cin >> size;
    int* arr = new int[size];
    initialize_array(arr, size);

    int number_of_threads;
    std::cout << "Enter number of threads: ";
    std::cin >> number_of_threads;

    std::vector<boost::thread> threads(number_of_threads);
    std::vector<ThreadInfo> information(number_of_threads);
    std::vector<bool> terminated_threads(number_of_threads, false);

    int ended_threads = 0;

    for (int i = 0; i < number_of_threads; ++i) {
        information[i].arr = arr;
        information[i].array_size = size;
        information[i].thread_index = i + 1;
        information[i].terminate_or_continue = new HANDLE[2];
        threads[i] = boost::thread(thread_func, boost::ref(information[i]));
    }

    for (int i = 0; i < number_of_threads; ++i) {
        threads[i].detach();
    }

    std::cout << "All threads are terminated\n";

    for (int i = 0; i < number_of_threads; ++i) {
        delete[] information[i].terminate_or_continue;
    }

    delete[] arr;

    return 0;
}