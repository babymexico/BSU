package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FactorialCalculator {
    public List<BigInteger> calculateFactorials(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("Number n should be non-negative.");
        }

        List<BigInteger> factorials = new ArrayList<>();
        BigInteger factorial = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
            factorials.add(factorial);
        }

        return factorials;
    }

    public static void main(String[] args) {
        FactorialCalculator calculator = new FactorialCalculator();
        Scanner scanner = new Scanner(System.in);

        try (scanner) {
            System.out.print("Enter a non-negative integer (n): ");
            int n = scanner.nextInt();
            List<BigInteger> calculatedFactorials = calculator.calculateFactorials(n);
            System.out.println("First " + n + " factorials:");
            for (int i = 0; i < calculatedFactorials.size(); i++) {
                System.out.println(i + 1 + "!: " + calculatedFactorials.get(i));
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
