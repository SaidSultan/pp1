package com.said.service;

import com.said.dao.DBHelper;
import com.said.dao.UserDao;
import com.said.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    public UserService () {}
/*    private static Connection getMySqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("Users?").
                    append("user=root&").
                    append("password=admin&").
                    append("serverTimezone=UTC");
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }*/

    private static UserDao getUserDao() {
        return new UserDao(DBHelper.getMySqlConnection());
    }

    public User getUserById(long id) { return getUserDao().getUserById(id); }
    public List<User> getAllUsers() {
        return getUserDao().getAllUsers();
    }
    public void addUser(User user){ getUserDao().addUser(user);}
    public void updateUser(User user){ getUserDao().updateUser(user); }
    public void deleteUser(User user) { getUserDao().deleteUSer(user.getId()); }
}
