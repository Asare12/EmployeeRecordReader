package com.sparta.da.EmployeeController;

import com.sparta.da.EmployeeModel.CSVReader;
import com.sparta.da.EmployeeModel.DAO;
import com.sparta.da.EmployeeModel.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

public class EmployeeManager {
    private CSVReader csvReader = new CSVReader();
    private DAO dao = new DAO();

    public void insertIntoDB(){

        Map<String, Employee> employeeMap = csvReader.FileReader();
        dao.insertHashMapSQLQuery(employeeMap);
    }
    public void truncateDB(){
        dao.truncateData();
    }


    public void printList(){
        Map<String, Employee> list = csvReader.FileReader();
        list.forEach((s, employee) -> System.out.println(employee));
    }


}
