#include "pch.h"
#include <gtest/gtest.h> // or any other testing framework
#include "../LabWork3/functions_to_test.h"

// Test set_zeros function
TEST(SetZerosTest, SetZerosWorks) {
    const int size = 5;
    int arr[size] = { 1, 2, 3, 4, 5 };

    set_zeros(arr, size, 3);

    ASSERT_EQ(arr[2], 0); // Third element should be set to 0
    ASSERT_NE(arr[1], 0); // Second element should remain unchanged
}

// Test mark_element function
TEST(MarkElementTest, MarkElementWorks) {
    const int size = 10;
    int arr[size] = { 0 };

    ThreadInfo info;
    info.arr = arr;
    info.array_size = size;
    info.thread_index = 1;
    info.end_thread = false;
    info.terminate_or_continue = new HANDLE[2]; // Mocked handles

    int markedElements = 0;
    mark_element(info, markedElements);

    // The test checks if the marked elements count is within the valid range
    ASSERT_GE(markedElements, 0);
    ASSERT_LE(markedElements, size);
}

// Test initialize_array function
TEST(InitializeArrayTest, InitializeArrayWorks) {
    const int size = 5;
    int arr[size] = { 1, 2, 3, 4, 5 };

    initialize_array(arr, size);

    for (int i = 0; i < size; ++i) {
        ASSERT_EQ(arr[i], 0); // All elements should be initialized to 0
    }
}

int main(int argc, char** argv) {
    testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
