package com.panda.crud.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.panda.crud.bean.User;
import com.panda.crud.bean.UserExample;
import com.panda.crud.bean.UserExample.Criteria;
import com.panda.crud.dao.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    
    /**
     * 保存用户
     * @param user
     */
    public void saveUser(User user) {
        userMapper.insertSelective(user);
    }
    
    /**
     * 根据用户名查询用户
     */
    public User getUserByUserName(String userName){
        UserExample example = new UserExample();
        Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(userName);
        return userMapper.selectByExample(example).get(0);
    }
    
    /**
     * 根据用户名获取用户角色
     */
    public Set<String> getRolesByName(String name){
        return null;
    }
    
    /**
     * 根据用户名获取用户权限
     */
    public Set<String> getPermissionsByName(String name){
        return null;
    }
}
