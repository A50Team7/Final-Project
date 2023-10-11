package com.testframework.api;

import com.testframework.Utils;

import java.sql.*;

public class UserControllerHelper {
    private static final String dbUrl = Utils.getConfigPropertyByKey("weare.db.url");
    private static final String username = Utils.getConfigPropertyByKey("weare.db.username");
    private static final String password = Utils.getConfigPropertyByKey("weare.db.password");
    private static final String deleteQuery =  "DELETE FROM users WHERE %s=%s;";
    private static final String findQuery = "SELECT * FROM users WHERE %s=%s;";

    private static ResultSet executeQuery(String query) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try(Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                return stmt.executeQuery(query);
            }
        } catch (SQLException e) {
            Utils.LOGGER.info(e);
        }
        return null;
    }

    private static ResultSet delete(String key, String value) {
        String query = String.format(deleteQuery, key, value);

        return executeQuery(query);
    }

    public static ResultSet getUser(String key, String value) {
        String query = String.format(findQuery, key, value);

        return executeQuery(query);
    }

    public static boolean userExists(ResultSet resultSet) {
        try {
            if (resultSet!=null) return resultSet.next();
            else {
                Utils.LOGGER.info("Unsuccessful query.");
            }
        } catch (SQLException e) {
            Utils.LOGGER.info(e);
        }
        return false;
    }

    public static int getUserId(ResultSet resultSet) {
        try {
            return resultSet.getInt("user_id");
        } catch (SQLException e) {
            Utils.LOGGER.info(e);
        }
        return 0;
    }

    public static void deleteUser(String key, String value) {
        if (userExists(getUser(key, value))) {
            delete(key, value);
            if (!userExists(getUser(key, value))) Utils.LOGGER.info("Successfully deleted user.");
            else Utils.LOGGER.info("Deleting user was unsuccessful.");
        }
        else Utils.LOGGER.info("No user found.");
    }

    public static int getUserIdByUsername(String username) {
        ResultSet resultSet = getUser("username", username);

        if (!userExists(resultSet)) Utils.LOGGER.info("No user found.");
        else return getUserId(resultSet);
        return 0;
    }

}
