package holecym.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class JdbcConnection {
    Connection connection;

    JdbcConnection() {
        String dbConnectString = System.getProperty("db.connection");
        String dbUser = System.getProperty("db.username");
        String dbPassword = System.getProperty("db.password");
        connection = getConnection(dbConnectString, dbUser, dbPassword);
    }

    // Protocols for SQLEXPRESS must be set to TCP/IP
    private Connection getConnection(String dbConnectString, String dbUser, String dbPassword) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (connection == null) {
                connection = DriverManager.getConnection(dbConnectString, dbUser, dbPassword);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    void closeConnections() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // do nothing
        }
    }
}
