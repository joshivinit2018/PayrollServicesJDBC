package com.bridgelabz;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    private Connection getConnection() throws SQLException{
        String jdbcURL = "jdbc:mysql://localhost:3307/payroll_service?useSSL=false";
        String username = "root";
        String password = "admin123";
        Connection connection;
        System.out.println("Connecting to database:" + jdbcURL);
        connection = DriverManager.getConnection(jdbcURL, username, password);
        System.out.println("Connection is successful!!!!!!" + connection);
        return connection;
    }

    public List<EmployeePayrollData> readData()
    {
        String sql = "SELECT * FROM employee_payroll; ";
        List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
        try
        {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = ((Statement) statement).executeQuery(sql);
            while(result.next())

            {
                int id  = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate startDate = result.getDate("start").toLocalDate();
                employeePayrollDataList.add(new EmployeePayrollData(id,name,salary, startDate));
            }
            connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return employeePayrollDataList;
    }



}
