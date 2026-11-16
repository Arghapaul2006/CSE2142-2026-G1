import java.io.*;
import java.util.*;

public class EmployeeManager {
    private static final String FILE_NAME = "employees.txt";
    private static final String MSG_LOADING = "Loading data ...";
    private static final String MSG_LOADED = "Data Loaded.";

    public static void main(String[] args) {
        // Task #2: Application Now Terminates Early if the Number of Arguments Passed is Wrong
        if (args == null || args.length == 0) {
            System.out.println("Error: No arguments provided. Please pass valid arguments like l, s, +, ?, c, u, d.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                String e[] = l.split(",");
                for (String emp : e) {
                    System.out.println(emp);
                }
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (args[0].equals("s")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                System.out.println(l);
                String e[] = l.split(",");
                Random rand = new Random();
                int idx = rand.nextInt(e.length);
                System.out.println(e[idx]);
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (args[0].contains("+")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedWriter w = new BufferedWriter(
                    new FileWriter(FILE_NAME, true));
                String n = args[0].substring(1);
                w.write(", " + n);
                w.close();
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (args[0].contains("?")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                String e[] = l.split(",");
                boolean found = false;
                String s = args[0].substring(1);
                for (int i = 0; i < e.length && !found; i++) {
                    if (e[i].equals(s)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (args[0].contains("c")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                char[] chars = l.toCharArray();
                boolean inWord = false;
                int count = 0;
                for (char c : chars) {
                    if (c == ' ') {
                        if (!inWord) {
                            count++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }
                System.out.println(count + " word(s) found " + chars.length);
            } catch (Exception e) {}
            System.out.println(MSG_LOADED);
        } else if (args[0].contains("u")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                for (int i = 0; i < e.length; i++) {
                    if (e[i].equals(n)) {
                        e[i] = "Updated";
                    }
                }
                BufferedWriter w = new BufferedWriter(
                    new FileWriter(FILE_NAME));
                w.write(String.join(",", e));
                w.close();
            } catch (Exception e) {}
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println(MSG_LOADING);
            try {
                BufferedReader r = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream(FILE_NAME)));
                String l = r.readLine();
                String e[] = l.split(",");
                String n = args[0].substring(1);
                List<String> list = new ArrayList<>(Arrays.asList(e));
                list.remove(n);
                BufferedWriter w = new BufferedWriter(
                    new FileWriter(FILE_NAME));
                w.write(String.join(",", list));
                w.close();
            } catch (Exception e) {}
            System.out.println("Data Deleted.");
        }
    }
}