package com.liyan.servlet;

import com.liyan.pojo.Users;
import com.liyan.service.Impl.UsersServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UsersServlet extends HttpServlet {
    private UsersServiceImpl usersService;
    @Override
    public void init() throws ServletException {
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        usersService=webApplicationContext.getBean(UsersServiceImpl.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        Users users=new Users();
        users.setUsername(username);
        users.setPassword(password);
        Users index=usersService.Login(users);
        if (index != null){
            resp.sendRedirect("mian.jsp");
        }else {
            resp.sendRedirect("login.jsp");
        }
    }
}
