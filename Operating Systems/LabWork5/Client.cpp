#include <windows.h>
#include <iostream>
#include <string>
#include <codecvt>
#include <conio.h>
#include "../LabWork5/employee.h"
#include "../LabWork5/message.h"

using namespace std;

const wchar_t* pipeName = L"\\\\.\\pipe\\named_pipe";

HANDLE openAndSetUpPipe() {
    HANDLE hPipe;
    DWORD cbRead, dwMode;
    while (1) {
        WaitNamedPipe(pipeName, INFINITE);
        hPipe = CreateFile(pipeName, GENERIC_READ | GENERIC_WRITE, 0, NULL, OPEN_EXISTING, 0, NULL);

        if (hPipe != INVALID_HANDLE_VALUE)
            break;

        if (GetLastError() != ERROR_PIPE_BUSY) {
            printf("Could not open pipe\n");
            return NULL;
        }

        if (!WaitNamedPipe(pipeName, 20000)) {
            printf("Could not open pipe\n");
            return NULL;
        }
    }

    dwMode = PIPE_READMODE_MESSAGE;
    boolean fSuccess = SetNamedPipeHandleState(hPipe, &dwMode, NULL, NULL);
    if (!fSuccess) {
        printf("SetNamedPipeHandleState failed.\n");
        return NULL;
    }

    return hPipe;
}

void modifyRecord(HANDLE hPipe) {
    while (true) {
        cout << "Enter ID\n";
        int id;
        cin >> id;
        HANDLE* event = new HANDLE[2];
        event[0] = OpenEvent(EVENT_ALL_ACCESS, true, (LPWSTR)wstring_convert<codecvt_utf8<wchar_t>>().from_bytes("r" + to_string(id)).c_str());
        if (event[0] != NULL) {
            event[1] = OpenEvent(EVENT_ALL_ACCESS, false, (LPWSTR)wstring_convert<codecvt_utf8<wchar_t>>().from_bytes("w" + to_string(id)).c_str());
            cout << "Wait, access to the entry may be blocked...\n";
            WaitForMultipleObjects(2, event, true, INFINITE);
            ResetEvent(event[1]);
            message msg;
            msg.id = id;
            msg.type = 'w';
            employee emp;
            TransactNamedPipe(hPipe, (char*)&msg, sizeof(msg), (char*)&emp, sizeof(emp), NULL, NULL);
            cout << "Employee name: " << emp.name << "\nNumber of hours worked: " << emp.hours << "\nEnter the changed name\n";
            cin >> emp.name;
            cout << "\nEnter the modified number of hours\n";
            cin >> emp.hours;
            DWORD dwRead;
            WriteFile(hPipe, (char*)&emp, sizeof(emp), &dwRead, NULL);
            cout << "\nPress any key to end access\n";
            _getch();
            SetEvent(event[1]);
            delete[] event;
            break;
        }
        else {
            cout << "\nThere is no employee with this number\n";
        }
    }
}

void readRecord(HANDLE hPipe) {
    while (true) {
        cout << "Enter ID\n";
        int id;
        cin >> id;
        HANDLE* event = new HANDLE[2];
        event[0] = OpenEvent(EVENT_ALL_ACCESS, true, (LPWSTR)wstring_convert<codecvt_utf8<wchar_t>>().from_bytes("r" + to_string(id)).c_str());
        if (event[0] != NULL) {
            event[1] = OpenEvent(EVENT_ALL_ACCESS, false, (LPWSTR)wstring_convert<codecvt_utf8<wchar_t>>().from_bytes("w" + to_string(id)).c_str());
            cout << "Wait, access to the entry may be blocked...\n";
            WaitForSingleObject(event[1], INFINITE);
            ResetEvent(event[0]);
            message msg;
            msg.id = id;
            msg.type = 'r';
            employee emp;
            TransactNamedPipe(hPipe, (char*)&msg, sizeof(msg), (char*)&emp, sizeof(emp), NULL, NULL);
            cout << "Employee name: " << emp.name << "\nNumber of hours worked: " << emp.hours << "\nPress any key to end access\n";
            _getch();
            SetEvent(event[0]);
            delete[] event;
            break;
        }
        else {
            cout << "\nThere is no employee with this number\n";
        }
    }
}

int main() {
    HANDLE hPipe = openAndSetUpPipe();
    if (hPipe == NULL) {
        return 1;
    }

    while (true) {
        cout << "1 - modify the record\n2 - read the record\n3 - exit\n";
        int ans;
        cin >> ans;
        if (ans == 1) {
            modifyRecord(hPipe);
        }
        else if (ans == 2) {
            readRecord(hPipe);
        }
        else {
            CloseHandle(hPipe);
            return 0;
        }
    }
}
