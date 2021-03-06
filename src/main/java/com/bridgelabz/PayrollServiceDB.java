package com.bridgelabz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class PayrollServiceDB {
    public static void main(String[] args){
        String jdbcURL = "jdbc:mysql://localhost:3307/payroll_service?useSSL=false";
        String username = "root";
        String password = "admin123";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded!");

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("cannot find the path in the driver!", e);
        }


        ListDrivers();
        try {
            System.out.println("Connecting to database: " + jdbcURL);
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("connection is successful!!!!"+connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ListDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" " + driverClass.getClass().getName());
        }
    }
}
