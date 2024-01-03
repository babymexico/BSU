package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesFromInputTest {

    @Test
    void testRemoveDuplicatesFromArray() {
        Integer[] inputArray = {1, 2, 2, 3, 4, 4, 5};
        List<Integer> uniqueList = RemoveDuplicatesFromInput.removeDuplicatesFromArray(inputArray);
        assertEquals(5, uniqueList.size());
        assertTrue(uniqueList.contains(1));
        assertTrue(uniqueList.contains(2));
        assertTrue(uniqueList.contains(3));
        assertTrue(uniqueList.contains(4));
        assertTrue(uniqueList.contains(5));
    }

    @Test
    void testRemoveDuplicatesFromArrayWithEmptyArray() {
        Integer[] inputArray = {};
        List<Integer> uniqueList = RemoveDuplicatesFromInput.removeDuplicatesFromArray(inputArray);
        assertTrue(uniqueList.isEmpty());
    }

    @Test
    void testRemoveDuplicatesFromInput() {
        String input = "1 2 2 3 4 4 5";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        RemoveDuplicatesFromInput.main(new String[]{});

        String expectedOutput = "Enter numbers separated by spaces: " +
                "Original Numbers: 1 2 2 3 4 4 5\n" +
                "Numbers without Duplicates: [1, 2, 3, 4, 5]\n";

        assertEquals(expectedOutput, getOutput());
    }

    // Helper method to capture console output
    private String getOutput() {
        return RemoveDuplicatesFromInput.getOutputStreamCaptor().toString();
    }
}
