package com.bridgelabz;

import java.util.List;

public class EmployeePayrollService {
    public enum IOService {DB_IO}

    private List<EmployeePayrollData> employeePayrollList;
    private List<EmployeePayrollData> employeePayrollDataList;

    private EmployeePayrollDBService employeePayrollDBService;


    public EmployeePayrollService()
    {
        employeePayrollDBService = EmployeePayrollDBService.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this();
        this.employeePayrollList = employeePayrollList;
    }

    public List<EmployeePayrollData> readEmployeePayrollData(IOService ioService)
    {
        if(ioService.equals(IOService.DB_IO))
            this.employeePayrollDataList = new EmployeePayrollDBService().readData();
        return this.employeePayrollDataList;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name)
    {
        List<EmployeePayrollData> employeePayrollDataList = employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
    }

    public void updateEmployeeSalary(String name, Double salary)
    {


        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if (result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            EmployeePayrollData.salary = salary;
    }
    public void updateEmployeeSalaryWithPreparedStatement(String name, Double salary)
    {
        int result = employeePayrollDBService.updateEmployeeDataUsingPreparedStatement(name, salary);
        if (result == 0) return;
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
        if (employeePayrollData != null)
            employeePayrollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayrollData(String name)
    {
        return this.employeePayrollList.stream().filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
    }


}
