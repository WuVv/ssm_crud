package com.panda.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.crud.bean.Department;
import com.panda.crud.bean.Msg;
import com.panda.crud.service.DepartmentService;

/**
 * 处理和部门相关的请求
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    /**
     * 返回所有的部门信息
     */
    @RequestMapping("depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> depts = departmentService.getDepts();
        return Msg.success().add("depts", depts);
    }
}
