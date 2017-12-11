package com.panda.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.crud.bean.Department;
import com.panda.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
    
}
