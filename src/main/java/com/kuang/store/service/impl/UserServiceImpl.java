package com.kuang.store.service.impl;

import com.kuang.store.entity.User;
import com.kuang.store.mapper.UserMapper;
import com.kuang.store.service.IUserService;
import com.kuang.store.service.ex.InsertException;
import com.kuang.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;


//用户模块Service实现类
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        User result = userMapper.findByUsername(user.getUsername());
        if (result != null){
            throw new UsernameDuplicatedException("用户名被占用");
        }
        // 密码加密处理：md5算法的形式
        //  盐值 + password + 盐值 -----> md5算法进行加密（连续加密三次），盐值就是一个随机的字符串
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 盐值也要存放数据库
        user.setSalt(salt);
        password = getMD5Password(password,salt);
        user.setPassword(password);
        // 用户数组补全操作
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows = userMapper.insert(user);
        if (rows != 1){
            throw new InsertException("用户注册过程中产生了未知异常");
        }
    }

    //md5算法加密
    private String getMD5Password(String password,String salt){
        for (int i=0;i<3;i++){
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
