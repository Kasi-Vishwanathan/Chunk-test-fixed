public class BuggyJava {

package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataProcessor {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int originalSize = list.size();
        for (int i = 0; i < originalSize; i++) {
            int num = list.get(i);
            if (num % 2 == 0) {
                list.add(num + 10);
            }
        }
        List<Integer> processedList = processData(list);
        System.out.println("Processed list: " + processedList);
    }

    public static List<Integer> processData(List<Integer> data) {
        return data.stream()
                .map(n -> n * 2)
                .collect(Collectors.toList());
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java DataProcessor <input_file>");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Integer> numbers = new ArrayList<>();
                boolean validLine = true;
                String[] parts = line.split(",");
                for (String part : parts) {
                    part = part.trim();
                    if (part.isEmpty()) {
                        continue;
                    }
                    try {
                        numbers.add(Integer.parseInt(part));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format '" + part + "' in line: " + line);
                        validLine = false;
                        break;
                    }
                }
                if (!validLine) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }
                if (numbers.isEmpty()) {
                    System.out.println("No valid numbers in line: " + line);
                    continue;
                }
                int sum = 0;
                for (int num : numbers) {
                    sum += num;
                }
                double average = (double) sum / numbers.size();
                System.out.println("Line sum: " + sum + ", average: " + average);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataProcessor {
    public static void main(String[] args) {
        List<String> dataLines = new ArrayList<>();
        dataLines.add("1,John Doe,2023-05-15,OPTIONAL_A");
        dataLines.add("2,Jane Smith,2023-05-16");
        dataLines.add("3,Bob Johnson,2023-05-17,OPTIONAL_B");
        dataLines.add("4,Alice,2023-05-18");

        HashMap<String, String> configMap = new HashMap<>();
        configMap.put("OPTIONAL_A", "ValueA");

        processData(dataLines, configMap);
    }

    public static void processData(List<String> data, HashMap<String, String> config) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<DataRecord> records = new ArrayList<>();

        for (String line : data) {
            String[] parts = line.split(",");
            if (parts.length > 3) {
                try {
                    String id = parts[0];
                    String name = parts[1];
                    LocalDate date = LocalDate.parse(parts[2], formatter);
                    String value = config.getOrDefault(parts[3], "DEFAULT");

                    DataRecord record = new DataRecord(id, name, date, value);
                    records.add(record);
                } catch (DateTimeParseException e) {
                    System.err.println("Skipping invalid date format in line: " + line);
                }
            }
        }

        System.out.println("Processed records:");
        records.forEach(System.out::println);
    }

    private static class DataRecord {
        private final String id;
        private final String name;
        private final LocalDate date;
        private final String extra;

        public DataRecord(String id, String name, LocalDate date, String extra) {
            this.id = id;
            this.name = name;
            this.date = date;
            this.extra = extra;
        }

        @Override
        public String toString() {
            return "DataRecord{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", date=" + date +
                    ", extra='" + extra + '\'' +
                    '}';
        }
    }
}
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public static void processData(List<String> data) {
        List<Integer> results = new ArrayList<>();
        for (String s : data) {
            if (!isValid(s)) {
                continue;
            }
            try {
                int num = Integer.parseInt(s);
                results.add(num);
            } catch (NumberFormatException e) {
                // Invalid number format, skip
            }
        }

        if (results.isEmpty()) {
            System.out.println("No valid data to calculate average.");
            return;
        }

        int sum = 0;
        for (int num : results) {
            sum += num;
        }
        double average = (double) sum / results.size();
        System.out.println("Average: " + average);
    }

    private static boolean isValid(String s) {
        return s != null && s.matches("^[+-]?\\d+$");
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("10");
        data.add("20");
        data.add("xxx");
        data.add("30");
        data.add("2147483648"); // Valid format but exceeds Integer range
        processData(data);
    }
}
package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataProcessor {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("5", "15", "10", "abc");
        List<Integer> results = processData(data, 10.0);
        System.out.println("Results: " + results);
    }

    public static List<Integer> processData(List<String> data, double threshold) {
        List<Integer> results = new ArrayList<>();
        for (String element : data) {
            try {
                int num = Integer.parseInt(element);
                if (num > threshold) {
                    results.add(num);
                }
            } catch (NumberFormatException e) {
                System.err.println("Error parsing element '" + element + "': " + e.getMessage());
            }
        }
        return results;
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        int total = 0;
        int count = 0;

        try (FileReader fileReader = new FileReader("data.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    try {
                        int value = Integer.parseInt(parts[2].trim());
                        total += value;
                        count++;
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                    }
                }
            }

            if (count > 0) {
                double average = (double) total / count;
                System.out.println("Average: " + average);
            } else {
                System.out.println("No valid data found to calculate average.");
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}