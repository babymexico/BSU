#include <string>
#include <Windows.h>
#include <conio.h>
#include <iostream>
#include <fstream>
#include "Employee.h"


const std::string CREATOR = "Creator.exe";
const std::string REPORTER = "Reporter.exe";

void runCreatorProcess(const std::string& binaryFileName, const std::string& amountOfNotes) {
    std::cout << "// Creator Process //\n\n";

    std::string messageToCreator = CREATOR + " " + binaryFileName + " " + amountOfNotes;
    std::wstring converterToLPWSTR(messageToCreator.begin(), messageToCreator.end());
    LPWSTR lpszCreatorMessage = &converterToLPWSTR[0];

    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    si.cb = sizeof(STARTUPINFO);

    if (!CreateProcess(NULL, lpszCreatorMessage, NULL, NULL, TRUE, CREATE_NEW_CONSOLE, NULL, NULL, &si, &pi)) {
        system("cls");
        std::cout << "Something went wrong with the Creator process";
        exit(GetLastError());
    }

    WaitForSingleObject(pi.hProcess, INFINITE);
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);
}

void runReporterProcess(const std::string& binaryFileName, const std::string& reportFileName, const std::string& salaryPerHour) {
    std::cout << "// Reporter Process //\n\n";

    std::string messageToReporter = REPORTER + " " + binaryFileName + " " + reportFileName + " " + salaryPerHour;
    std::wstring converterToLPWSTR(messageToReporter.begin(), messageToReporter.end());
    LPWSTR lpszReporterMessage = &converterToLPWSTR[0];

    STARTUPINFO si;
    PROCESS_INFORMATION pi;
    ZeroMemory(&si, sizeof(STARTUPINFO));
    si.cb = sizeof(STARTUPINFO);

    if (!CreateProcess(NULL, lpszReporterMessage, NULL, NULL, TRUE, CREATE_NEW_CONSOLE, NULL, NULL, &si, &pi)) {
        system("cls");
        std::cout << "Something went wrong with the Reporter process";
        exit(GetLastError());
    }

    WaitForSingleObject(pi.hProcess, INFINITE);
    CloseHandle(pi.hProcess);
    CloseHandle(pi.hThread);
}

void displayReport(const std::string& reportFileName) {
    std::ifstream reportInput(reportFileName);
    if (reportInput.is_open()) {
        std::cout << "// Report //\n\n";
        std::string temp;
        while (getline(reportInput, temp)) {
            std::cout << temp << "\n";
        }
        reportInput.close();
    }
}

int main() {
    std::cout << "// Main Process //\n\n";

    std::string binaryFileName, amountOfNotes, reportFileName, salaryPerHour;

    std::cout << "Enter binary file name: ";
    std::cin >> binaryFileName;

    std::cout << "Enter the number of notes in this file: ";
    std::cin >> amountOfNotes;

    runCreatorProcess(binaryFileName, amountOfNotes);

    std::cout << "Enter report file name: ";
    std::cin >> reportFileName;

    std::cout << "Enter salary per hour: ";
    std::cin >> salaryPerHour;

    runReporterProcess(binaryFileName, reportFileName, salaryPerHour);

    displayReport(reportFileName);

    _getch();

    return 0;
}
