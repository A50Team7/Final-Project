package com.testframework.api;

import com.testframework.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserControllerHelper {
    public static void deleteUser(String key, String value) {
        String dbUrl = Utils.getConfigPropertyByKey("weare.db.url");
        String username = Utils.getConfigPropertyByKey("weare.db.username");
        String password = Utils.getConfigPropertyByKey("weare.db.password");
        String query = String.format("DELETE FROM users WHERE %s=%s;", key, value);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try(Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(query);
                Utils.LOGGER.info("Successfully deleted the user.");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
