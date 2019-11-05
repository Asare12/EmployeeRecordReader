package com.sparta.da;

import com.sparta.da.EmployeeController.EmployeeManager;
import com.sparta.da.EmployeeModel.CSVReader;
import com.sparta.da.EmployeeModel.DAO;
import com.sparta.da.EmployeeModel.Employee;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Map;

public class PerformanceTest {
    private DAO dao;
    private CSVReader csvReader;
    private Map<String, Employee> list;


    @Before
    public void setup() {
        dao = new DAO();
        csvReader = new CSVReader();
        dao.truncateData();

    }

    @Test
    public void InsertQueryPerformanceTest(){
           list =  csvReader.FileReader();
           long start = System.currentTimeMillis();
           dao.insertHashMapSQLQuery(list);
           long end = System.currentTimeMillis();
           printResult(end -start);

    }
    @Test
    public void insertHashMapWithThreadsSQLQueryTest(){
        list =  csvReader.FileReader();
        long start = System.currentTimeMillis();
        dao.insertHashMapWithThreadsSQLQuery(list);
        long end = System.currentTimeMillis();
        printResult(end -start);

    }

    private void printResult(long time){
        DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
        System.out.println("It took: " + decimalFormat.format(time) + " milli seconds to complete the query" );
    }
}
