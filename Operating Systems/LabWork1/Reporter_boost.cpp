#include <iostream>
#include <fstream>
#include <boost/archive/binary_iarchive.hpp>
#include <boost/serialization/vector.hpp>
#include "employee_boost.h"

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
    boost::archive::binary_iarchive inputArchive(inFile);

    while (true) {
        Employee emp;
        try {
            inputArchive >> emp;
        } catch (boost::archive::archive_exception&) {
            break;
        }

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
