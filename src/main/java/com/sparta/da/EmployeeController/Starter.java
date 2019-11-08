package com.sparta.da.EmployeeController;

import com.sparta.da.EmployeeModel.CSVReader;
import com.sparta.da.EmployeeModel.DAO;
import com.sparta.da.EmployeeModel.Employee;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Starter
{
    public static void main( String[] args )
    {

        new EmployeeManager().truncateDB();

        new EmployeeManager().insertIntoDB();

        new EmployeeManager().printList();
    }
}
