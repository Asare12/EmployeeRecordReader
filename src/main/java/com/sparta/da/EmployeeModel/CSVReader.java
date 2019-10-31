package com.sparta.da.EmployeeModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class CSVReader {
    private static final String PATH = "resources/testEmployees.csv";
    private Map<String, Employee> list = new HashMap<>();

    public Map<String, Employee> FileReader() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
            csvReader.readLine();
            csvReader.lines()
                    .map(filename -> filename.split(","))
                    .forEach(employeeInfo -> list.put(employeeInfo[0], new Employee(employeeInfo[0], employeeInfo[1], employeeInfo[2], employeeInfo[3], employeeInfo[4], employeeInfo[5], employeeInfo[6], formatDate(employeeInfo[7]),formatDate(employeeInfo[8]), Integer.parseInt(employeeInfo[9]))));
//
//            String row;
//            csvReader.readLine();
//
//            while ((row = csvReader.readLine()) != null) {
//                String[] data = row.split(",");
//                list.put(data[0], new Employee(data[0], data[1], data[2], data[3], data[4], data[5], data[6], formatDate(data[7]),formatDate(data[8]), Integer.parseInt(data[9])));
//            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private LocalDate formatDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate localDate = LocalDate.parse(str, dateTimeFormatter);
        return localDate;
    }
}
