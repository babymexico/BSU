#include <iostream>
#include <string>
#include <fstream>
#include "Employee.h"

bool validateArguments(int argc, char* argv[]) {
    if (argc != 3) {
        std::cerr << "Using: " << argv[0] << " file_name countEmployee" << std::endl;
        return false;
    }
    return true;
}

bool openFileForWriting(const char* fileName, std::ofstream& outputFileStream) {
    outputFileStream.open(fileName, std::ios::out | std::ios::binary);
    if (!outputFileStream.is_open()) {
        std::cerr << "The file could not be opened for writing." << std::endl;
        return false;
    }
    return true;
}

void writeEmployeeData(std::ofstream& outputFileStream, int countEmployee) {
    outputFileStream.write(reinterpret_cast<char*>(&countEmployee), sizeof(int));

    for (int i = 0; i < countEmployee; ++i) {
        employee emp;

        std::cout << "Enter id of employee: ";
        std::cin >> emp.num;
        std::cout << "Enter name of employee: ";
        std::cin >> emp.name;
        std::cout << "Enter number of hours of employee: ";
        std::cin >> emp.hours;

        outputFileStream.write(reinterpret_cast<char*>(&emp), sizeof(employee));
    }
}

int main(int argc, char* argv[]) {
    if (!validateArguments(argc, argv))
        return 1;

    const char* file_name = argv[1];
    int countEmployee = std::stoi(argv[2]);

    std::ofstream outputFileStream;
    if (!openFileForWriting(file_name, outputFileStream))
        return 1;

    writeEmployeeData(outputFileStream, countEmployee);

    outputFileStream.close();
    std::cout << "The file was created successfully." << std::endl;

    return 0;
}
