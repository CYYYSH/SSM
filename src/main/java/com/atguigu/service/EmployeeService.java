package com.atguigu.service;

import com.atguigu.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> selectAllEmp();


    public int insertEmp(Employee employee);

    public int  findEmpByName(String empName);

    public Employee getEmp(Integer id);

    public int saveEmp(Employee employee);

    public int delete(Integer id);

    public void deleteBatch(List<Integer> integers);
}
