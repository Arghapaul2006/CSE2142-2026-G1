import java.io.*;
import java.util.*;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_LOADED = "Data Loaded.";

    public static void main(String[] args) {
        // Task #2 & #6: Validate arguments and handle invalid inputs gracefully
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
        } else {
            System.out.println("Error: Invalid operation argument provided.");
        }
    }

    // Task #4 & #5: Helper method for reading file with proper exception handling
    private static String readEmployeeData() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(FILE_NAME)))) {
            String line = reader.readLine();
            if (line != null) {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file " + FILE_NAME + " was not found.");
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred while reading the file.");
        }
        return content.toString();
    }

    // Task #4 & #5: Helper method for writing file with proper exception handling
    private static void writeEmployeeData(String data, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, append))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error: An I/O error occurred while writing to the file.");
        }
    }

    private static void loadAndPrintAllEmployees() {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (!fileData.isEmpty()) {
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
        if (!fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            Random random = new Random();
            int randomIndex = random.nextInt(employeeArray.length);
            System.out.println(employeeArray[randomIndex].trim());
        }
        System.out.println(MSG_LOADED);
    }

    private static void addEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String newEmployeeName = operation.substring(1).trim();
        if (!newEmployeeName.isEmpty()) {
            writeEmployeeData(", " + newEmployeeName, true);
            System.out.println("Employee Added Successfully.");
        }
        System.out.println(MSG_LOADED);
    }

    private static void searchEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (!fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            boolean isFound = false;
            String targetEmployee = operation.substring(1).trim();
            for (String employee : employeeArray) {
                if (employee.trim().equals(targetEmployee)) {
                    System.out.println("Employee found!");
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("Employee not found.");
            }
        }
        System.out.println(MSG_LOADED);
    }

    // Task #7 & #9: Refined word and character counting logic with clean stream/string formatting
    private static void countCharactersAndWords() {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (!fileData.isEmpty()) {
            String trimmedData = fileData.trim();
            if (trimmedData.isEmpty()) {
                System.out.println("0 word(s) found 0");
            } else {
                String[] words = trimmedData.split(",");
                int charCount = trimmedData.length();
                System.out.println(words.length + " word(s) found " + charCount);
            }
        } else {
            System.out.println("0 word(s) found 0");
        }
        System.out.println(MSG_LOADED);
    }

    private static void updateEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (!fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            String targetName = operation.substring(1).trim();
            for (int i = 0; i < employeeArray.length; i++) {
                if (employeeArray[i].trim().equals(targetName)) {
                    employeeArray[i] = "Updated";
                }
            }
            writeEmployeeData(String.join(",", employeeArray), false);
        }
        System.out.println("Data Updated Successfully.");
    }

    private static void deleteEmployee(String operation) {
        System.out.println(MSG_LOADING);
        String fileData = readEmployeeData();
        if (!fileData.isEmpty()) {
            String[] employeeArray = fileData.split(",");
            String targetName = operation.substring(1).trim();
            List<String> employeeList = new ArrayList<>(Arrays.asList(employeeArray));
            employeeList.removeIf(emp -> emp.trim().equals(targetName));
            writeEmployeeData(String.join(",", employeeList), false);
        }
        System.out.println("Data Deleted Successfully.");
    }
}