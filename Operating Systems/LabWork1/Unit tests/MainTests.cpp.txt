#include "pch.h"
#include "../LabWork1/Employee.h"
#include "gtest/gtest.h"

// Google Test cases
TEST(EmployeeProcessTest, TestRunCreatorProcess) {
    std::string testBinaryFileName = "testBinary.exe";
    std::string testAmountOfNotes = "10";

    ASSERT_NO_THROW(runCreatorProcess(testBinaryFileName, testAmountOfNotes));
}

TEST(EmployeeProcessTest, TestRunReporterProcess) {
    std::string testBinaryFileName = "testBinary.exe";
    std::string testReportFileName = "testReport.txt";
    std::string testSalaryPerHour = "20";

    ASSERT_NO_THROW(runReporterProcess(testBinaryFileName, testReportFileName, testSalaryPerHour));
}

TEST(EmployeeProcessTest, TestDisplayReport) {
    std::string testReportFileName = "testReport.txt";

    ASSERT_NO_THROW(displayReport(testReportFileName));
}

int main(int argc, char** argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}