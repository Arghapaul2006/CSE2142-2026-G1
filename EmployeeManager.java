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

        // Task #3: Using meaningful variable names instead of single letters
        if (operation.equals("l")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                String[] employeeArray = fileLine.split(",");
                for (String employee : employeeArray) {
                    System.out.println(employee);
                }
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (operation.equals("s")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                System.out.println(fileLine);
                String[] employeeArray = fileLine.split(",");
                Random random = new Random();
                int randomIndex = random.nextInt(employeeArray.length);
                System.out.println(employeeArray[randomIndex]);
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (operation.contains("+")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(FILE_NAME, true));
                String newEmployeeName = operation.substring(1);
                bufferedWriter.write(", " + newEmployeeName);
                bufferedWriter.close();
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (operation.contains("?")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                String[] employeeArray = fileLine.split(",");
                boolean isFound = false;
                String targetEmployee = operation.substring(1);
                for (int i = 0; i < employeeArray.length && !isFound; i++) {
                    if (employeeArray[i].equals(targetEmployee)) {
                        System.out.println("Employee found!");
                        isFound = true;
                    }
                }
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (operation.contains("c")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                char[] charArray = fileLine.toCharArray();
                boolean isInWord = false;
                int wordCount = 0;
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
                System.out.println(wordCount + " word(s) found " + charArray.length);
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (operation.contains("u")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                String[] employeeArray = fileLine.split(",");
                String targetName = operation.substring(1);
                for (int i = 0; i < employeeArray.length; i++) {
                    if (employeeArray[i].equals(targetName)) {
                        employeeArray[i] = "Updated";
                    }
                }
                BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(FILE_NAME));
                bufferedWriter.write(String.join(",", employeeArray));
                bufferedWriter.close();
            } catch (Exception e) {}
            System.out.println("Data Updated.");
        } else if (operation.contains("d")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String fileLine = bufferedReader.readLine();
                String[] employeeArray = fileLine.split(",");
                String targetName = operation.substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeArray));
                employeeList.remove(targetName);
                BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter(FILE_NAME));
                bufferedWriter.write(String.join(",", employeeList));
                bufferedWriter.close();
            } catch (Exception e) {}
            System.out.println("Data Deleted.");
        }
    }
}