package com.panda.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panda.crud.bean.Employee;
import com.panda.crud.dao.DepartmentMapper;
import com.panda.crud.dao.EmployeeMapper;

/**
 * 测试dao层
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-tx.xml"})
public class MapperTest {
    
    @Autowired
    DepartmentMapper departmentMapper;
    
    @Autowired
    EmployeeMapper employeeMapper;
    
    @Autowired
    SqlSession sqlSession;
    
    @Test
    public void testCURD(){
        /*departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));*/
        
//        employeeMapper.insertSelective(new Employee(null, "smith", "F", "smith@panda.com", 1));
//        employeeMapper.insertSelective(new Employee(null, "marry", "M", "marry@panda.com", 2));
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uuid = UUID.randomUUID().toString().substring(0, 5)+i;
            
            if(i%4==0){
                mapper.insertSelective(new Employee(null, uuid, "M", uuid+"@panda.com", 2));
            }else if(i%3==0){
                mapper.insertSelective(new Employee(null, uuid, "F", uuid+"@panda.com", 2));
            }else {
                mapper.insertSelective(new Employee(null, uuid, "M", uuid+"@panda.com", 1));
            }
            
        }
        System.out.println("批量完成");
    }
}
