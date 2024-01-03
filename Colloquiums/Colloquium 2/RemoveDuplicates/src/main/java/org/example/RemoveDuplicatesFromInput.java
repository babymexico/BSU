package org.example;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveDuplicatesFromInput {
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter numbers separated by spaces: ");
        String inputLine = scanner.nextLine();

        // Split the input string into numbers using space as a delimiter
        String[] numberStrings = inputLine.split("\\s+");

        // Convert strings to integers
        Integer[] numbers = new Integer[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        List<Integer> uniqueNumbers = removeDuplicatesFromArray(numbers);

        System.out.println("Original Numbers: " + String.join(" ", numberStrings));
        System.out.println("Numbers without Duplicates: " + uniqueNumbers);

        scanner.close();
    }

    public static <T> List<T> removeDuplicatesFromArray(T[] array) {
        List<T> uniqueList = new ArrayList<>();
        for (T element : array) {
            if (!uniqueList.contains(element)) {
                uniqueList.add(element);
            }
        }
        return uniqueList;
    }

    public static ByteArrayOutputStream getOutputStreamCaptor() {
        return outputStreamCaptor;
    }
}
