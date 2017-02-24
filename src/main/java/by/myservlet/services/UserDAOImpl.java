package by.myservlet.services;

import by.myservlet.model.User;
import by.myservlet.utils.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create on 9.2.17.
 *
 * @author egor
 */
@Repository
public class UserDAOImpl implements UserDAO {

    static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class.getName());

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    private static class Queries {
        static final String CREATE = "INSERT INTO users (name, last_name, password) VALUES (?, ? , ?)";
        static final String UPDATE = "UPDATE users SET name = ?, last_name = ?, password = ? WHERE id = ?";
        static final String DELETE = "DELETE FROM users WHERE id = ?";
        static final String FIND_BY_NUMBER = "SELECT ALL * FROM users";
        static final String FIND_BY_NAME = "SELECT * FROM users WHERE name = ?";
        static final String FIND_BY_ID = "Select * FROM users WHERE id = ?";
    }

    @Override
    public void create(User user) {
        connection = ConnectionManager.getConnection();
        if (user != null) {
            try {
                preparedStatement = connection.prepareStatement(Queries.CREATE);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.execute();
                //TODO: replace sout with logback or log4j
                log.info("User is added successfully");
            } catch (SQLException e) {
                //TODO: replace e.printStackTrace() with logback or log4j
                log.error("Failed to connect database: ", e);
            }
        } else {
            log.info("Failed to Create User. User must be not NULL");
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
            log.info("User updated successfully");
        } catch (SQLException e) {
            log.error("Failed to connect database: ", e);
        }
    }

    @Override
    public void delete(long id) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            log.info("User with id = " + id + " is deleted successfully");
        } catch (SQLException e) {
            log.error("Failed to connect database: ", e);
        }
    }

    @Override
    public List<User> findAll() {
        Statement st = null;
        try {
            st = ConnectionManager.getConnection().createStatement();
            rs = st.executeQuery(Queries.FIND_BY_NUMBER);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                List<User> users = new ArrayList<>();
                users.add(new User(id, name, lastname, password));
            }
        } catch (SQLException e) {
            log.error("Failed to connect database: ", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    log.error("Failed to connect database: ", e);
                }
            }
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.FIND_BY_NAME);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setPassword(password);
                return user;
            }
        } catch (SQLException e) {
            log.error("Failed to connect database: ", e);
        }
        return null;
    }

    @Override
    public User findById(long id) {
        connection = ConnectionManager.getConnection();
        try {
            preparedStatement = connection.prepareStatement(Queries.FIND_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String lastname = rs.getString("last_name");
                String password = rs.getString("password");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastname);
                user.setPassword(password);
                return user;
            }
        } catch (SQLException e) {
            log.error("Failed to connect database: ", e);
        }
        return null;
    }
}
