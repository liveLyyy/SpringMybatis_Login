package com.liyan.test;

import com.liyan.pojo.Users;
import com.liyan.service.Impl.UsersServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsersTest {
    @Test
    public void Login(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:beans.xml");
        UsersServiceImpl usersService=applicationContext.getBean("usersService",UsersServiceImpl.class);
        Users users=new Users();
        users.setUsername("root");
        users.setPassword("root");
        Users index=usersService.Login(users);
        if (index != null){
            System.out.println("登录成功");
            System.out.println(index);
        }else {
            System.out.println("登录失败");
        }
    }
}
