#include <iostream>
#include <fstream>
#include <string>
#include <boost/process.hpp>

const std::string CREATOR = "Creator.exe";
const std::string REPORTER = "Reporter.exe";

namespace bp = boost::process;

void runCreatorProcess(const std::string& binaryFileName, const std::string& amountOfNotes) {
    std::cout << "// Creator Process //\n\n";

    bp::system(CREATOR + " " + binaryFileName + " " + amountOfNotes);
}

void runReporterProcess(const std::string& binaryFileName, const std::string& reportFileName, const std::string& salaryPerHour) {
    std::cout << "// Reporter Process //\n\n";

    bp::system(REPORTER + " " + binaryFileName + " " + reportFileName + " " + salaryPerHour);
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

    std::cin.get(); // To keep console window open
    return 0;
}
