package services;

import model.User;
import utils.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by egor on 9.2.17.
 */
public class IUserOperationsImpl implements IUserOperations{

    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    private static class Queries{
        private static final String CREATE = "INSERT INTO users (name, last_name, password) VALUES ('%s', '%s', '%s')";
        private static final String UPDATE = "UPDATE users SET name = '%s', last_name ='%s', password = '%s' WHERE id =%d";
        private static final String DELETE = "DELETE FROM users WHERE id = %d";
        private static final String FIND_BY_NUMBER = "SELECT ALL * FROM users";
        public static final String FIND_BY_NAME = "SELECT * FROM users WHERE name ='%s'";

    }
    @Override
    public void create(User user) {
        connection = ConnectionManager.getConnection();
        try {
            statement = connection.createStatement();
            String query = String.format(Queries.CREATE, user.getName(), user.getLastName(), user.getPassword());
            statement.execute(query);
            System.out.println("User added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        connection = ConnectionManager.getConnection();
        try {
            statement = connection.createStatement();
            String query = String.format(Queries.UPDATE, user.getName(), user.getLastName(), user.getPassword(), user.getId());
            statement.execute(query);
            System.out.println("User updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(long id) {
        connection = ConnectionManager.getConnection();
        try {
            statement = connection.createStatement();
            String query = String.format(Queries.DELETE,id);
            statement.execute(query);
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
            statement = connection.createStatement();
            String query = String.format(Queries.FIND_BY_NAME,name);
            rs = statement.executeQuery(query);
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
    public User findById(int id) {
        //TODO: fix this
        return new User(id, "","");
    }
}
