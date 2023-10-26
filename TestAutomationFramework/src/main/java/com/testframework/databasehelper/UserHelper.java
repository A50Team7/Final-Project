package com.testframework.databasehelper;

import com.testframework.Utils;

import java.sql.*;

/**
 * A utility class for user-related database operations, extending DatabaseHelper.
 *
 * @see DatabaseHelper
 */
public class UserHelper extends DatabaseHelper{

    private static final String tableName = "users";

    /**
     * Retrieves a user based on the specified key and value.
     *
     * @param key the key to search for
     * @param value the value corresponding to the key
     * @return the result set containing the user
     */
    public static ResultSet getUser(String key, String value) {
        return getEntity(tableName, key, value);
    }

    /**
     * Retrieves the user ID from the result set.
     *
     * @param resultSet the result set containing the user
     * @return the ID of the user
     */
    public static int getUserId(ResultSet resultSet) {
        return getEntityId(resultSet, "user_id");
    }

    /**
     * Deletes a user based on the specified key and value.
     *
     * @param key the key to search for
     * @param value the value corresponding to the key
     */
    public static void deleteUser(String key, String value) {
        deleteEntity("user", tableName, key, value);
    }

    /**
     * Retrieves the user ID based on the provided username.
     *
     * @param username the username to search for
     * @return the ID of the user
     */
    public static int getUserIdByUsername(String username) {
        return getEntityIdByKey("user", tableName, "username", username, "user_id");
    }

}
