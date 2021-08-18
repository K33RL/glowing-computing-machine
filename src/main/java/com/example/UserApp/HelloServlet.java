package com.example.UserApp;

import com.example.UserApp.dao.UserDao;
import com.example.UserApp.entity.User;

import java.io.*;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    UserDao userDao;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log("init servlet");
        userDao = new UserDao();
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userDao.getAll();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("do get");
        for (User user : users) {
            out.println(user.toString() + "\n");
        }
    }

    public void destroy() {
        log("method destroy");
    }
}