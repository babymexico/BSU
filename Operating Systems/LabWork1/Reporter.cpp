#include <iostream>
#include <fstream>
#include <string>
#include "Employee.h"

bool openBinaryFile(const char* fileName, std::ifstream& inFile) {
    inFile.open(fileName, std::ios::binary);
    if (!inFile.is_open()) {
        std::cerr << "The binary file could not be opened." << std::endl;
        return false;
    }
    return true;
}

bool createReportFile(const char* fileName, std::ofstream& outFile) {
    outFile.open(fileName);
    if (!outFile.is_open()) {
        std::cerr << "The report file could not be created." << std::endl;
        return false;
    }
    return true;
}

void processEmployeeData(std::ifstream& inFile, std::ofstream& outFile, double moneyPerHour) {
    while (true) {
        employee emp;
        inFile.read(reinterpret_cast<char*>(&emp), sizeof(employee));
        if (inFile.eof())
            break;

        outFile << emp.num << " " << emp.name << " " << emp.hours << " " << moneyPerHour * emp.hours << '\n';
    }
}

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cerr << "Using: " << argv[0] << " binaryFile reportName moneyPerHour" << std::endl;
        return 1;
    }

    const char* binaryFile = argv[1];
    const char* reportName = argv[2];
    double moneyPerHour = std::stod(argv[3]);

    std::ifstream inFile;
    std::ofstream outFile;

    if (!openBinaryFile(binaryFile, inFile))
        return 1;

    if (!createReportFile(reportName, outFile)) {
        inFile.close();
        return 1;
    }

    processEmployeeData(inFile, outFile, moneyPerHour);

    inFile.close();
    outFile.close();

    return 0;
}
