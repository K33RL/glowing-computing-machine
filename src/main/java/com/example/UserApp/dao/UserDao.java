package com.example.UserApp.dao;

import com.example.UserApp.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements AbstractDao<User> {
    private static final String USER_ADD = "INSERT INTO users (user_name, user_surname, user_age) " +
            "VALUES (?,?,?)";
    private static final String USER_GET_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String UPDATE_USER = "UPDATE users SET user_name=?, user_surname=?, user_age=? WHERE user_id=?";
    private static final String DELETE_USER = "DELETE FROM users Where user_id=?";


    @Override
    public void create(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_ADD)) {
            System.out.println("Successful connection");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getAge());

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByID(int id) {
        User user = new User();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(USER_GET_BY_ID)) {
            System.out.println("Successful connection");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("user_surname"));
            user.setAge(resultSet.getLong("user_age"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            System.out.println("Successful connection");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setSurname(resultSet.getString("user_surname"));
                user.setAge(resultSet.getLong("user_age"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        boolean updated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            System.out.println("Successful connection");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getAge());
            preparedStatement.setLong(4, user.getId());
            updated = preparedStatement.executeUpdate() > -1;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            System.out.println("Successful connection");
            preparedStatement.setLong(1, id);
            deleted = preparedStatement.executeUpdate() > -1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deleted;

    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres",
                "postgres",
                "admin");
    }

}
