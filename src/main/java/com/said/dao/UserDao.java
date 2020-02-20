package com.said.dao;

import com.said.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {this.connection = connection;}



    public void addUser(User user) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO user (name, lastname, age) VALUES ('" + user.getName() + "', '" +
                    user.getLastName() + "','" + user.getAge() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE user SET name='" + user.getName() + "', lastName='"+ user.getLastName() + "', " +
                    "age='" + user.getAge() + "' WHERE id='"+ user.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUSer(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id= ?")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(long id){
        String name;
        String lastName;
        byte age;
        try(Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM user WHERE id='"+ id + "'");
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()){
                name = resultSet.getString("name");
                lastName = resultSet.getString("lastName");
                age = resultSet.getByte("age");
                resultSet.close();
                return new User(id, name, lastName, age);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            statement.execute("SELECT * FROM USER");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age")));
            }
            resultSet.close();
            return users;
        } catch (SQLException e) {
            return null;
        }
    }




    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS User(id bigint auto_increment, name varchar(30), lastName varchar(40), " +
                "age tinyint, primary key (id))");
        statement.close();
    }
    public void dropTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS USER");
        statement.close();
    }
}
