package com.kuang.store.service;

import com.kuang.store.entity.User;
import com.kuang.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServicTests {
    @Autowired
    private IUserService userService;

    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("李四");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }
}
