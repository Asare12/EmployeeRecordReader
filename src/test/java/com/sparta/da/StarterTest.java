package com.sparta.da;

import com.sparta.da.EmployeeModel.CSVReader;
import com.sparta.da.EmployeeModel.DAO;
import com.sparta.da.EmployeeModel.Employee;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class StarterTest {
    private DAO dao;
    private CSVReader csvReader;

    @Before
    public void setup() {
        dao = new DAO();
        csvReader = new CSVReader();
    }

    @Test
    public void TestAddEmployee() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        Employee employee = new Employee("1", "Mr.", "David", "Paul", "Asare", "Male", "david@gmail.com", LocalDate.parse("4/28/1994", dateTimeFormatter), LocalDate.parse("10/28/2019", dateTimeFormatter), 100000);
        dao.InsertEachSQLQuery(employee);
    }


    @Test
    public void TestAddEmployeeWithCSVFile(){
        Map<String, Employee> list = csvReader.FileReader();
        dao.InsertHashMapSQLQuery(list);
    }

    @Test
    public void TestSizeOfEmployeeFile(){
        int list = csvReader.FileReader().size();
        assertEquals(21,list);
    }

    @Test
    public void TestCSVFileIsEmpty(){
        assertFalse(csvReader.FileReader().isEmpty());
    }
}
