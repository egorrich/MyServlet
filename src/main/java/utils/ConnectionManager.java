package utils;

import java.sql.*;

/**
 * Created by egor on 8.2.17.
 */
public class ConnectionManager {

    private static final String DRIVER = "org.postgresql.Driver";

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";

    private static final String USER = "postgres";

    private static final String PASSWORD = "postgres";


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            System.out.println("Driver is loaded");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected succesfully");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }


}
