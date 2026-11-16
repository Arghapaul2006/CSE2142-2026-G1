import java.io.*;
import java.util.*;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_LOADED = "Data Loaded.";

    public static void main(String[] args) {
        // Task #2: Application Terminates Early if Arguments are Missing
        if (args == null || args.length == 0) {
            System.out.println("Error: No arguments provided. Please pass valid arguments like l, s, +, ?, c, u, d.");
            return;
        }

        String operation = args[0];

        if (operation.equals("l")) {
            loadAndPrintAllEmployees();
        } else if (operation.equals("s")) {
            loadAndPrintRandomEmployee();
        } else if (operation.contains("+")) {
            addEmployee(operation);
        } else if (operation.contains("?")) {
            searchEmployee(operation);
        } else if (operation.contains("c")) {
            countCharactersAndWords();
        } else if (operation.contains("u")) {
            updateEmployee(operation);
        } else if (operation.contains("d")) {
            deleteEmployee(operation);
        }
    }

    // Task #4: Helper method to avoid code duplication for reading file
    private static String readEmployeeData() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME)))) {
            content.append(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return content.toString();
    }

    // Task #4: Helper method to avoid code duplication for writing file
    private static void writeEmployeeData(String data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, append))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }
    }

    private static void loadAndPrintAllEmployees() {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            for (String employee : employeeArray) {
                System.out.println(employee.trim());
            }
        }
        System.out.println(MSG_LOADED);
    }

    private static void loadAndPrintRandomEmployee() {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            Random random = new Random();
            int randomIndex = random.nextInt(employeeArray.length);
            System.out.println(employeeArray[randomIndex].trim());
        }
        System.out.println(MSG_LOADED);
    }

    private static void addEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String newEmployeeName = operation.substring(1);
        writeEmployeeData(", " + newEmployeeName, true);
        System.out.println(MSG_LOADED);
    }

    private static void searchEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            boolean isFound = false;
            String targetEmployee = operation.substring(1);
            for (String employee : employeeArray) {
                if (employee.trim().equals(targetEmployee)) {
                    System.out.println("Employee found!");
                    isFound = true;
                    break;
                }
            }
        }
        System.out.println(MSG_LOADED);
    }

    private static void countCharactersAndWords() {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            char[] charArray = fileData.toCharArray();
            int wordCount = 0;
            boolean isInWord = false;
            for (char ch : charArray) {
                if (ch == ' ') {
                    if (!isInWord) {
                        wordCount++;
                        isInWord = true;
                    } else {
                        isInWord = false;
                    }
                }
            }
            if (charArray.length > 0) {
                wordCount++;
            }
            System.out.println(wordCount + " word(s) found " + charArray.length);
        }
        System.out.println(MSG_LOADED);
    }

    private static void updateEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            String targetName = operation.substring(1);
            for (int i = 0; i < employeeArray.length; i++) {
                if (employeeArray[i].trim().equals(targetName)) {
                    employeeArray[i] = "Updated";
                }
            }
            writeEmployeeData(String.join(",", employeeArray), false);
        }
        System.out.println("Data Updated.");
    }

    private static void deleteEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (fileData != null && !fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            String targetName = operation.substring(1);
            List<String> employeeList = new ArrayList<>(Arrays.asList(employeeArray));
            employeeList.removeIf(emp -> emp.trim().equals(targetName));
            writeEmployeeData(String.join(",", employeeList), false);
        }
        System.out.println("Data Deleted.");
    }
}