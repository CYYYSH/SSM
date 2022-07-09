package com.atguigu.service;


import com.atguigu.dao.DepartmentMapper;
import com.atguigu.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public List<Department> selectAllDepts() {


        List<Department> departments = departmentMapper.selectByExample(null);

        return departments;
    }
}
