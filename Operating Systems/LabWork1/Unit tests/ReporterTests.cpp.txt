#include "pch.h"
#include <gtest/gtest.h>
#include <sstream>
#include <fstream>
#include "../LabWork1/Employee.h"
#include "../Reporter/Reporter.h"

// Test fixture for file-related functions
class FileProcessorTest : public ::testing::Test {
protected:
    void SetUp() override {
        // Create test binary file
        std::ofstream binaryFile("testBinaryFile.bin", std::ios::binary);
        employee emp{ 1, "John", 40.0 };
        binaryFile.write(reinterpret_cast<char*>(&emp), sizeof(employee));
        binaryFile.close();
    }

    void TearDown() override {
        // Remove test files
        remove("testBinaryFile.bin");
        remove("testReport.txt");
    }
};

// Test openBinaryFile function
TEST_F(FileProcessorTest, TestOpenBinaryFile) {
    std::ifstream inFile;
    ASSERT_TRUE(openBinaryFile("testBinaryFile.bin", inFile));
    inFile.close();
}

// Test createReportFile function
TEST_F(FileProcessorTest, TestCreateReportFile) {
    std::ofstream outFile;
    ASSERT_TRUE(createReportFile("testReport.txt", outFile));
    outFile.close();
}

// Test processEmployeeData function
TEST(ProcessEmployeeDataTest, TestProcessEmployeeData) {
    // Prepare file streams for testing
    std::ifstream inFile("testBinaryFile.bin", std::ios::binary);
    std::ofstream outFile("testReport.txt");
    ASSERT_TRUE(inFile.is_open());
    ASSERT_TRUE(outFile.is_open());

    // Process employee data
    processEmployeeData(inFile, outFile, 20.0);

    // Close the file streams
    inFile.close();
    outFile.close();

    // Read from created report file to verify content
    std::ifstream reportFile("testReport.txt");
    std::string content;
    std::getline(reportFile, content);
    std::string expectedOutput = "1 John 40 800";
    ASSERT_EQ(content, expectedOutput);
    reportFile.close();
}
