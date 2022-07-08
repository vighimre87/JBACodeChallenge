package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private int Xref;
    private int Yref;
    private String date;
    private int value;

    private static List<String[]> createCsvFile() {
        String[] header = {"Xref", "Yref", "Date", "Value"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        return list;
    }

    public static void main(String[] args) {
	// write your code here
            try {
                File myObj = new File("C:\\Users\\anton\\Downloads\\jba-software-code-challenge-data-transformation\\cru-ts-2-10.1991-2000-cutdown.pre");
                Scanner scanner = new Scanner(myObj);
                List<Integer> integers = new ArrayList<>();
                while (scanner.hasNext()) {
                    String data = scanner.nextLine();
                    if (data.contains("Grid-ref=")) {
                        System.out.println(data);
                    }
//                    if (scanner.hasNextInt()) {
//                        integers.add(scanner.nextInt());
//                    }
//                    else {
//                        scanner.next();
//                    }
                }
//                System.out.println(integers);
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred");
                e.printStackTrace();
            }
            createCsvFile();

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\anton\\databases\\codingchallenge.db");
                conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS precipitation "+
                    "(Xref INTEGER, Yref INTEGER, Date TEXT, Value INTEGER)");

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
