package services;

import model.User;
import utils.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by egor on 9.2.17.
 */
public class UserDAOImpl implements UserDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    private static class Queries{
        private static final String CREATE = "INSERT INTO users (name, last_name, password) VALUES (?, ? , ?)";
        private static final String UPDATE = "UPDATE users SET name = ?, last_name = ?, password = ? WHERE id = ?";
        private static final String DELETE = "DELETE FROM users WHERE id = ?";
        private static final String FIND_BY_NUMBER = "SELECT ALL * FROM users";
        public static final String FIND_BY_NAME = "SELECT * FROM users WHERE name = ?";
        public static final String FIND_BY_ID = "Select * FROM users WHERE id = ?";

    }

    @Override
    public void create(User user) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.CREATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
            System.out.println("User added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.UPDATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
            System.out.println("User updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void delete(long id) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("User with id = " + id + " is deleted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<User> findAll(int number) {
        Statement st = null;
        List<User> users = new ArrayList<>();
        try {
            st = ConnectionManager.getConnection().createStatement();
            rs = st.executeQuery(Queries.FIND_BY_NUMBER);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                users.add(new User(id, name, lastname, password));
            }
            if (users.size() < number) {
                return users;
            }
            if (users.size() > number) {
                users = users.subList(0, number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        Statement st = null;
        List<User> users = new ArrayList<>();
        try {
            st = ConnectionManager.getConnection().createStatement();
            rs = st.executeQuery(Queries.FIND_BY_NUMBER);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                users.add(new User(id,name, lastname, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public User findByName(String name) {
        connection = ConnectionManager.getConnection();
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(Queries.FIND_BY_NAME);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("User " + name + " is not found");
        }
        return user;
    }

    @Override
    public User findById(long id) {
        connection = ConnectionManager.getConnection();
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(Queries.FIND_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("User " + id + " is not found");
        }
        return user;
    }
}
