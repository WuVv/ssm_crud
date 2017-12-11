package com.panda.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.crud.bean.Employee;
import com.panda.crud.bean.EmployeeExample;
import com.panda.crud.bean.EmployeeExample.Criteria;
import com.panda.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工
     * @return
     */
    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     * @param employee
     */
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 检查员工名是否可用
     * @param empName
     * @return ture 可用    false 不可用
     */
    public boolean checkUser(String empName) {
        
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        return employeeMapper.countByExample(example) == 0;
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新员工信息
     * @param emp
     */
    public void updateEmp(Employee emp) {
        employeeMapper.updateByPrimaryKeySelective(emp);
    }

    /**
     * 删除单个员工
     * @param id
     */
    public void deleteEmpById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除员工
     */
    public void deleteBitch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

}
