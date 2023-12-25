Task description:

"Write a method named 'fill' that accepts an array of objects and an implementation of the 'Function' interface (or your own).
The 'fill' method should populate the array by obtaining a new value for each index using the 'Function' interface implementation. In other words, it should be used as follows:
```java
public static void main(String[] args) {
    Integer[] squares = new Integer[100];
    fill(squares, integer -> integer * integer); // 0, 1, 4, 9, 16
}
```

The task is to create a method called 'fill' that takes an array of objects and a 'Function' interface implementation. This 'fill' method should populate the array by applying the function to generate values for each index in the array."