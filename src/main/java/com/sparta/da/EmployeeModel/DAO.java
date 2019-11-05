package com.sparta.da.EmployeeModel;


import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class DAO {

    private final String QUERY = "INSERT INTO employee (idEmployee, namePrefix, firstName, middleName, lastName, gender, email, dateOfBirth, dateJoin, salary)" +
                                 "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String URL = "jdbc:mysql://localhost/employee?user=root&password=Mymother08";
    private CSVReader csvReader = new CSVReader();
    private Employee[] employees;
    private int employeeCount = 0;
    private int threadCount = 0;


    public void insertEachSQLQuery(Employee employee){
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1, employee.getEmployeeID());
            statement.setString(2, employee.getNamePrefix());
            statement.setString(3, employee.getFirstName());
            statement.setString(4, employee.getMiddleName());
            statement.setString(5, employee.getLastName());
            statement.setString(6, employee.getGender());
            statement.setString(7, employee.getEmail());
            statement.setDate(8, Date.valueOf(employee.getDOB()));
            statement.setDate(9, Date.valueOf(employee.getDateJoin()));
            statement.setInt(10, employee.getSalary());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertArray(Employee[] employeeData){

        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            for (Employee employee: employees){
                statement.setString(1, employee.getEmployeeID());
                statement.setString(2, employee.getNamePrefix());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getMiddleName());
                statement.setString(5, employee.getLastName());
                statement.setString(6, employee.getGender());
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDOB()));
                statement.setDate(9, Date.valueOf(employee.getDateJoin()));
                statement.setInt(10, employee.getSalary());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void insertHashMapWithThreadsSQLQuery(Map<String, Employee> data){
        employees = csvReader.returnMap().values().toArray(new Employee[csvReader.returnMap().size()]);
        Thread [] threads = new Thread[150];

        employeeCount = employees.length;
        threadCount = threads.length;
       // int factor  = employeeCount/threadCount;

        for (int i = 0; i < threads.length; i++) {
            final int j = i;
            Runnable runnable;
            if((employeeCount*(i+1))/threadCount>employeeCount){
                runnable = () -> insertArray(Arrays.copyOfRange(employees, (employeeCount*j)/threadCount,employees.length));
            }else {
                runnable = () -> insertArray(Arrays.copyOfRange(employees, (employeeCount*j)/threadCount, (employeeCount* (j + 1))/threadCount));
            }
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            Collection<Employee> employees = data.values();
            for (Employee employee: employees){
                statement.setString(1, employee.getEmployeeID());
                statement.setString(2, employee.getNamePrefix());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getMiddleName());
                statement.setString(5, employee.getLastName());
                statement.setString(6, employee.getGender());
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDOB()));
                statement.setDate(9, Date.valueOf(employee.getDateJoin()));
                statement.setInt(10, employee.getSalary());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void insertHashMapSQLQuery(Map<String, Employee> data){

        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            Collection<Employee> employees = data.values();
            for (Employee employee: employees){
                statement.setString(1, employee.getEmployeeID());
                statement.setString(2, employee.getNamePrefix());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getMiddleName());
                statement.setString(5, employee.getLastName());
                statement.setString(6, employee.getGender());
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDOB()));
                statement.setDate(9, Date.valueOf(employee.getDateJoin()));
                statement.setInt(10, employee.getSalary());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void truncateData() {
        final String QUERY = "TRUNCATE TABLE employee";
        final String URL = "jdbc:mysql://localhost/employee?user=root&password=Mymother08";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.executeUpdate(QUERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
