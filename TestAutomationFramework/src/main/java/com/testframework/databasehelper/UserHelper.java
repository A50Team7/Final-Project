package com.testframework.databasehelper;

import com.testframework.Utils;

import java.sql.*;


public class UserHelper extends DatabaseHelper{

    private static final String tableName = "users";

    public static ResultSet getUser(String key, String value) {
        return getEntity(tableName, key, value);
    }

    public static int getUserId(ResultSet resultSet) {
        return getEntityId(resultSet, "user_id");
    }

    public static void deleteUser(String key, String value) {
        deleteEntity("user", tableName, key, value);
    }

    public static int getUserIdByUsername(String username) {
        return getEntityIdByKey("user", tableName, "username", username, "user_id");
    }

}
