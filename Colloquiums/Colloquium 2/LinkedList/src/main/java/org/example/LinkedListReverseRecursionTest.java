package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListReverseRecursionTest {

    @Test
    void testReverseEmptyList() {
        LinkedListReverseRecursion list = new LinkedListReverseRecursion();
        list.reverse();
        assertNull(list.head, "Empty list should remain empty after reverse");
    }

    @Test
    void testReverseSingleNode() {
        LinkedListReverseRecursion list = new LinkedListReverseRecursion();
        list.add(10);
        list.reverse();
        assertEquals(10, list.head.data, "Single node list should remain unchanged after reverse");
        assertNull(list.head.next, "Single node list should remain unchanged after reverse");
    }

    @Test
    void testReverseMultipleNodes() {
        LinkedListReverseRecursion list = new LinkedListReverseRecursion();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        int[] originalValues = {1, 2, 3, 4};
        Node originalHead = list.head;

        list.reverse();

        int[] reversedValues = new int[originalValues.length];
        Node reversedHead = list.head;

        int i = 0;
        while (reversedHead != null) {
            reversedValues[i++] = reversedHead.data;
            reversedHead = reversedHead.next;
        }

        assertArrayEquals(originalValues, reversedValues, "Reversed list should contain elements in reverse order");

        // Ensure the original head is now the last node after reversal
        Node current = list.head;
        while (current.next != null) {
            current = current.next;
        }
        assertEquals(originalHead, current, "Original head should be the last node after reversal");
    }
}

