package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BuggyJava350 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) {
                iterator.remove();
            }
        }
        
        System.out.println("List after removal: " + list);
        
        try {
            list.get(5);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BuggyJavaTest {

    @Test
    public void testSubtract() {
        assertEquals(3, BuggyJava.subtract(10, 7), "10 - 7 should equal 3");
    }

    @Test
    public void testFactorial() {
        assertEquals(120, BuggyJava.factorial(5), "Factorial of 5 should be 120");
    }

    @Test
    public void testArray() {
        int[] arr = BuggyJava.createArray(3);
        assertEquals(3, arr.length, "Array length should be 3");
        assertEquals(2, arr[0], "arr[0] should be 2");
        assertEquals(4, arr[1], "arr[1] should be 4");
        assertEquals(6, arr[2], "arr[2] should be 6");
    }

    @Test
    public void testCreateArrayNegativeSize() {
        assertThrows(NegativeArraySizeException.class, () -> BuggyJava.createArray(-1),
            "Creating array with negative size should throw NegativeArraySizeException");
    }
}
public class BuggyJava {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;

        for (int i = 0; i < 5; i++) { // Fixed loop condition
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);

        String s = ""; // Initialized to non-null
        System.out.println(s.length()); // Now safe

        int a = 10, b = 2; // Changed b to non-zero
        System.out.println("Division: " + (a / b));

        int[] arr = {1, 2, 3};
        System.out.println(arr[2]); // Corrected index

        for (int i = 0; i < 5; i++) { // Added braces
            System.out.println("i: " + i);
        }
        System.out.println("End of loop");

        int j = 0;
        while (j < 3) { // Added braces
            System.out.println("While loop: " + j);
            j++;
        }

        System.out.println("Program ends.");
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 2 * (i + 1);
        }
        return arr;
    }
}
import java.util.Scanner;

public class BuggyJava350_chunk4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = input.nextInt();

        if (num % 2 == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }

        input.close();
    }
}
package com.example;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        processData(data, processor);
        System.out.println("Data processing completed.");
    }

    public static void processData(List<Integer> data, DataProcessor processor) {
        List<Long> processedData = new ArrayList<>();
        for (int n : data) {
            processedData.add(processor.processNumber(n));
        }
        System.out.println("Processed data: " + processedData);
    }

    public long processNumber(int number) {
        long num = number;
        return num * num;
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProcessor {
    public static void main(String[] args) {
        List<Integer> data = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        try {
            data.get(5);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
        
        int sum = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) % 2 == 0) {
                sum += data.get(i);
            }
        }
        System.out.println("Sum: " + sum);
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuggyJava350 {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> processedList = processList(numbers);
        System.out.println(processedList);
    }

    public static List<Integer> processList(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Integer num = list.get(i);
            if (num % 2 == 0) {
                result.add(num * 2);
            } else {
                result.add(num / 2);
            }
        }
        return result;
    }
}
// File: buggy_java_test.java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BuggyJavaTest {

    @Test
    public void testSubtract() {
        assertEquals(3, BuggyJava.subtract(10, 7), "10 - 7 should equal 3");
    }

    @Test
    public void testFactorial() {
        assertEquals(120, BuggyJava.factorial(5), "Factorial of 5 should be 120");
    }

    @Test
    public void testArray() {
        int[] arr = BuggyJava.createArray(3);
        assertEquals(3, arr.length, "Array length should be 3");
        assertEquals(2, arr[0], "arr[0] should be 2");
        assertEquals(4, arr[1], "arr[1] should be 4");
        assertEquals(6, arr[2], "arr[2] should be 6");
    }

    @Test
    public void testCreateArrayNegativeSize() {
        assertThrows(NegativeArraySizeException.class, () -> BuggyJava.createArray(-1),
            "Creating array with negative size should throw NegativeArraySizeException");
    }
}

class BuggyJava {
    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 2 * (i + 1);
        }
        return arr;
    }
}
package com.example;

public class MyLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        size = 0;
    }
}
public class BuggyJava {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) { // Fixed loop condition
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);

        String s = ""; // Initialize to empty string
        System.out.println("String length: " + s.length()); // Now 0

        int a = 10, b = 2; // Changed b to 2 to avoid division by zero
        System.out.println("Division: " + (a / b)); // Now 5

        int[] arr = {1, 2, 3};
        System.out.println("Last element: " + arr[2]); // Last element

        for (int i = 0; i < 5; i++) { // Added braces
            System.out.println("i: " + i);
            System.out.println("End of loop iteration");
        }

        int j = 0;
        while (j < 3) { // Added braces
            System.out.println("While loop: " + j);
            j++;
        }

        System.out.println("Program ends.");
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 2 * (i + 1);
        }
        return arr;
    }
}
public class BuggyJava {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;

        for (int i = 0; i < 5; i++) { // Corrected loop condition
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);

        String s = ""; // Initialized to empty string
        System.out.println(s.length()); // Now safe

        int a = 10, b = 2; // Changed divisor to 2
        System.out.println("Division: " + (a / b));

        int[] arr = {1, 2, 3};
        System.out.println(arr[2]); // Corrected index

        for (int i = 0; i < 5; i++) { // Added braces
            System.out.println("i: " + i);
            System.out.println("End of loop");
        }

        int j = 0;
        while (j < 3) { // Added braces
            System.out.println("While loop: " + j);
            j++; // Now increments correctly
        }

        System.out.println("Program ends.");
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 2 * (i + 1);
        }
        return arr;
    }
}
public class BuggyJava {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = 0;

        for (int i = 0; i < numbers.length; i++) { // Fixed loop condition
            sum += numbers[i];
        }
        System.out.println("Sum: " + sum);

        String s = ""; // Initialized to an empty string
        System.out.println("String length: " + s.length());

        int a = 10, b = 0;
        if (b != 0) {
            System.out.println("Division: " + (a / b));
        } else {
            System.out.println("Division by zero is undefined.");
        }

        int[] arr = {1, 2, 3};
        if (arr.length > 3) { // Check bounds before access
            System.out.println(arr[3]);
        } else {
            System.out.println("Index 3 is out of bounds for the array.");
        }

        for (int i = 0; i < 5; i++) { // Added braces for loop body
            System.out.println("i: " + i);
            System.out.println("End of loop iteration");
        }

        int j = 0;
        while (j < 3) { // Added braces to include increment in loop
            System.out.println("While loop: " + j);
            j++;
        }

        System.out.println("Program ends.");
    }

    // Method to subtract two integers
    public static int subtract(int a, int b) {
        return a - b;
    }

    // Method to compute factorial of a number
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is undefined for negative numbers.");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // Method to create an array with specified size, elements as 2*(index+1)
    public static int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 2 * (i + 1);
        }
        return arr;
    }
}