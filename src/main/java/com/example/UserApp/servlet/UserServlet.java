package com.example.UserApp.servlet;

import com.example.UserApp.dao.UserDao;
import com.example.UserApp.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDao userDao;
    private int age;

    public UserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertUser(request, response);
                break;
            case "/delete":
                deleteUser(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateUser(request, response);
                break;
            default:
                listUser(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
        requestDispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(name, surname, age);
        userDao.create(user);

        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete(id);
        response.sendRedirect("list");

    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User tempUser;
        tempUser = userDao.getByID(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", tempUser);
        requestDispatcher.forward(request, response);

    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        Long age = Long.parseLong(request.getParameter("age"));
        User user = new User(id, name, surname, age);

        userDao.update(user);
        response.sendRedirect("list");

    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<User> listUsers = userDao.getAll();
            request.setAttribute("listUser", listUsers);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user-list.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}