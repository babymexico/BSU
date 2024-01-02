package org.example;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            System.out.print("Enter the value of n: ");
            int n = scanner.nextInt();
            FactorialCalculator calculator = new FactorialCalculator();
            List<BigInteger> factorials = calculator.calculateFactorials(n);
            System.out.println("First " + n + " factorials:");
            for (int i = 0; i < factorials.size(); i++) {
                System.out.println((i + 1) + "!: " + factorials.get(i));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}