package com.sparta.da.EmployeeController;

import com.sparta.da.EmployeeModel.CSVReader;
import com.sparta.da.EmployeeModel.DAO;
import com.sparta.da.EmployeeModel.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {

    public void getHashMap(){
        CSVReader csvReader = new CSVReader();
        DAO dao = new DAO();
        Map<String, Employee> list = csvReader.FileReader();
        dao.InsertHashMapSQLQuery(list);
    }

}
