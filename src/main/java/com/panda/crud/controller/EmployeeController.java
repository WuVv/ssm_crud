package com.panda.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.panda.crud.bean.Employee;
import com.panda.crud.bean.Msg;
import com.panda.crud.service.EmployeeService;

/**
 * 处理员工CRUD请求
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    /**
     * 检查员工名是否可用
     */
    @RequestMapping("checkUser")
    @ResponseBody
    public Msg checkUser(String empName){
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if(!empName.matches(regx)){
           return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        boolean bl = employeeService.checkUser(empName);
        return bl?Msg.success():Msg.fail().add("va_msg", "用户名已存在");
    }
    
    /**
     * 分页查询员工数据
     * @return
     */
    @RequestMapping("emps")
    @ResponseBody
    public Msg getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn){
        
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", page);
    }
    
    /**
     * 保存员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value="emp",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee,BindingResult result){
        //后端校验存在异常
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error:errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }
    
    /**
     * 根据id查询员工信息
     * @param id
     */
    @RequestMapping(value="emp/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }
    
    /**
     * 更新员工
     */
    @RequestMapping(value="emp/{empId}",method=RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee emp){
        employeeService.updateEmp(emp);
        return Msg.success();
    }
    
    /**
     * 删除员工(单个/批量)
     */
    @RequestMapping(value="emp/{id}",method=RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmpById(@PathVariable("id")String id){
        if(id.contains("-")){
            List<Integer> del_ids = new ArrayList<Integer>();
            String[] ids = id.split("-");
            for (String string : ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBitch(del_ids);
        }else {
            employeeService.deleteEmpById(Integer.parseInt(id));
        }
        return Msg.success();
    }
}
