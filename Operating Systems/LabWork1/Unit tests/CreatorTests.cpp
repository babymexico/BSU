#include "pch.h"
#include <gtest/gtest.h>
#include <fstream>
#include <sstream>
#include "../LabWork1/Employee.h"
#include "../Creator/Creator.h"

// ValidateArguments test suite
TEST(ValidateArgumentsTest, TestValidArguments) {
    char* args[] = { "program_name", "file.txt", "5" };
    int argc = sizeof(args) / sizeof(args[0]);
    ASSERT_TRUE(validateArguments(argc, args));
}

TEST(ValidateArgumentsTest, TestInvalidArguments) {
    char* args[] = { "program_name", "file.txt" };
    int argc = sizeof(args) / sizeof(args[0]);
    ASSERT_FALSE(validateArguments(argc, args));
}

// OpenFileForWriting test suite
TEST(OpenFileForWritingTest, TestFileOpening) {
    std::ofstream outputFileStream;
    const char* file_name = "testOutputFile.bin";
    ASSERT_TRUE(openFileForWriting(file_name, outputFileStream));
    outputFileStream.close();
}

// WriteEmployeeData test suite
TEST(WriteEmployeeDataTest, TestDataWriting) {
    const char* file_name = "testEmployeeFile.bin";
    int countEmployee = 2;

    std::ofstream outputFileStream;
    ASSERT_TRUE(openFileForWriting(file_name, outputFileStream));

    // Prepare input data
    std::istringstream iss("1 John 40.0\n2 Alice 35.5\n");
    std::streambuf* cinBackup = std::cin.rdbuf(iss.rdbuf());

    // Redirect cout to capture output
    std::stringstream ss;
    std::streambuf* coutBackup = std::cout.rdbuf(ss.rdbuf());

    // Write employee data
    writeEmployeeData(outputFileStream, countEmployee);

    // Restore cin and cout
    std::cin.rdbuf(cinBackup);
    std::cout.rdbuf(coutBackup);

    outputFileStream.close();

    // Verify file contents
    std::ifstream inputFileStream(file_name, std::ios::binary);
    int storedCount;
    inputFileStream.read(reinterpret_cast<char*>(&storedCount), sizeof(int));

    employee emp1, emp2;
    inputFileStream.read(reinterpret_cast<char*>(&emp1), sizeof(employee));
    inputFileStream.read(reinterpret_cast<char*>(&emp2), sizeof(employee));

    inputFileStream.close();

    ASSERT_EQ(storedCount, countEmployee);
    ASSERT_EQ(emp1.num, 1);
    ASSERT_STREQ(emp1.name, "John");
    ASSERT_DOUBLE_EQ(emp1.hours, 40.0);
    ASSERT_EQ(emp2.num, 2);
    ASSERT_STREQ(emp2.name, "Alice");
    ASSERT_DOUBLE_EQ(emp2.hours, 35.5);
}
