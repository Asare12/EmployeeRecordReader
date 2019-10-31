package com.sparta.da.EmployeeModel;


import java.net.URL;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DAO {

    private final String QUERY = "INSERT INTO employee (idEmployee, namePrefix, firstName, middleName, lastName, gender, email, dateOfBirth, dateJoin, salary)" +
                                 "VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final String URL = "jdbc:mysql://localhost/employee?user=root&password=Mymother08";
    Employee employee;

    public void InsertEachSQLQuery(Employee employee){
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
    public void InsertHashMapSQLQuery(Map<String, Employee> data){
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


}
