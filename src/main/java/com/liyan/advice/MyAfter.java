package com.liyan.advice;

import com.liyan.pojo.Users;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public class MyAfter implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        Logger logger=Logger.getLogger(String.valueOf(MyAfter.class));
        Users users= (Users) objects[0];
        if (o != null) {
            logger.info(users.getUsername()+"登录成功");
        }else {
            logger.info(users.getUsername()+"登录成功");
        }
    }
}
