package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create on 8.2.17.

 * @author egor
 */
public class ConnectionManager {
    static final Logger log = LoggerFactory.getLogger(ConnectionManager.class.getName());

    private static final String DRIVER = "org.postgresql.Driver";

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            log.info("Driver is loaded");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            log.info("Connected succesfully");
        }catch (ClassNotFoundException e) {
            log.error("Can't find driver: " + e);
        }
        catch (SQLException e) {
            log.error("Can't connect to database: " + e);
        }
        return conn;
    }


}
