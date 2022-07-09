package com.atguigu.service;


import com.atguigu.dao.EmployeeMapper;
import com.atguigu.pojo.Employee;
import com.atguigu.pojo.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;


    public List<Employee>  selectAllEmp(){
        List<Employee> employees = employeeMapper.selectByExampleWithDept();
        return  employees;
    }

    @Override
    public int insertEmp(Employee employee) {
        int i = employeeMapper.insertSelective(employee);

        return i;
    }

    @Override
    public int findEmpByName(String empName) {

        EmployeeExample employeeExample = new EmployeeExample();

        employeeExample.createCriteria().andEmpNameEqualTo(empName);

        int count = employeeMapper.countByExample(employeeExample);

        return count;


    }

    @Override
    public Employee getEmp(Integer id) {

        Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);

        return  employee;
    }

    @Override
    public int saveEmp(Employee employee) {


        int i = employeeMapper.updateByPrimaryKeySelective(employee);

        return i;
    }

    @Override
    public int delete(Integer id) {

        int i = employeeMapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public void deleteBatch(List<Integer> integers) {


        EmployeeExample employeeExample = new EmployeeExample();

         employeeExample.createCriteria().andEmpIdIn(integers);

        int i = employeeMapper.deleteByExample(employeeExample);


    }
}
