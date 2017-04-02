package holecym.dao;

import holecym.dao.converters.DtoConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Michal on 17. 1. 2017.
 */
public class SimpleDao<T> {
    Connection connection;

    SimpleDao() {
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

    public Set<T> getSimpleQuery(String sqlQuery, DtoConverter<T> dtoConverter) {
        final Set<T> result = new LinkedHashSet<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                while (resultSet.next()) {
                    dtoConverter.convert(result, resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
        return result;
    }
}
