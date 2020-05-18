package pl.coderslab.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    static private final String DB_URL = "jdbc:mysql://localhost:3306/books?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    static private final String DB_USER = "root";
    static private final String DB_PASS = "coderslab";

    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        return connection;
    }
}