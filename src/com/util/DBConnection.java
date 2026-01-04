package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/jodidotcom?useSSL=false&serverTimezone=UTC";

    private static final String USER = "jodiuser";
    private static final String PASSWORD = "jodipass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver NOT Found");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
