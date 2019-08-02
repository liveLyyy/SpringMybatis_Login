package com.liyan.advice;

import com.liyan.pojo.Users;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Logger;

public class MyBefore implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        Users users= (Users) objects[0];
        Logger logger=Logger.getLogger(String.valueOf(MyBefore.class));
        logger.info(users.getUsername()+"在"+new Date().toLocaleString()+"进行登录");
    }
}
