package com.panda.crud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.panda.crud.bean.Msg;
import com.panda.crud.bean.User;
import com.panda.crud.realm.MyRealm;
import com.panda.crud.service.UserService;
import com.panda.crud.utils.MD5Util;

/**
 * 处理用户请求
 * @author Administrator
 *
 */
@Controller
public class UserController {
    
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 保存用户
     * @return
     */
    @RequestMapping(value="user",method=RequestMethod.POST)
    @ResponseBody
    public Msg saveUser(@Valid User user,BindingResult result){
        //后端校验存在异常
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error:errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        }else{
            //对用户密码进行1024次MD5加密，盐值为用户Id
            user.setPassword(MD5Util.md5(user.getPassword(), 
                    user.getId().toString()));
            userService.saveUser(user);
            return Msg.success();
        }
    }
    
    /**
     * 用户登录
     * 
     */
    @RequestMapping(value = "login",method=RequestMethod.POST)
    public String login(@RequestParam("userName") String userName, 
            @RequestParam("password") String password){
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(
                    userName, password, true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                logger.info("认证失败！！！");
                return "login";
            }
            
        }
        return "employee";
    }
    
}
