package com.wipro.delivery.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getDBConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
            String user = "system";
            String pass = "717823f253";
            
            Connection connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);  // Required for transactions
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Oracle JDBC Driver missing: " + e.getMessage(), e);
        }
    }
}
