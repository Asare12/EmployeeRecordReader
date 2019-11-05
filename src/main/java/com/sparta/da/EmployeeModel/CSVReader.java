package com.sparta.da.EmployeeModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CSVReader{
    private static final String PATH = "resources/EmployeeRecords.csv";
    private Map<String, Employee> employeeList = new HashMap<>();
    private Map<String, Employee> employeeDuplicateList = new HashMap<>();

    public Map<String, Employee> FileReader() {
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH));

            String row;
            csvReader.readLine();

            while ((row = csvReader.readLine()) != null) {
                String[] employee = row.split(",");
                Employee emp = new Employee(employee[0], employee[1], employee[2], employee[3], employee[4], employee[5], employee[6], formatDate(employee[7]), formatDate(employee[8]), Integer.parseInt(employee[9]));
                if (employeeList.containsKey(employee[0])) {
                    employeeDuplicateList.put(employee[0], emp);
                } else {
                    employeeList.put(employee[0], emp);
                }
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* System.out.println("Size of duplicate list: " + employeeDuplicateList.size());
        System.out.println("Size of list: " + employeeList.size());*/
        return employeeList;
    }

    public Map<String, Employee> returnMap(){
        return employeeList;
    }

    public Map<String, Employee> FileReaderWithLambda() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));
            bufferedReader.readLine();

            bufferedReader.lines()
                    .filter(s -> !s.startsWith("Emp ID"))
                    .map(filename -> filename.split(","))
                   // .limit(5)
                    .forEach(employeeInfo -> employeeList.put(employeeInfo[0], new Employee(employeeInfo[0], employeeInfo[1], employeeInfo[2], employeeInfo[3], employeeInfo[4], employeeInfo[5], employeeInfo[6], formatDate(employeeInfo[7]),formatDate(employeeInfo[8]), Integer.parseInt(employeeInfo[9]))));
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private LocalDate formatDate(String str){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate localDate = LocalDate.parse(str, dateTimeFormatter);
        return localDate;
    }
}
