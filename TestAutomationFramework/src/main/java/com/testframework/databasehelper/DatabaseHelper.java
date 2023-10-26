package com.testframework.databasehelper;

import com.testframework.Utils;

import java.sql.*;

/**
 * A utility class for database operations.
 *
 * @see UserHelper
 * @see PostHelper
 * @see CommentHelper
 * @see ConnectionHelper
 * @see RequestsHelper
 */
public class DatabaseHelper {
    private static final String dbUrl = Utils.getConfigPropertyByKey("weare.db.url");
    private static final String username = Utils.getConfigPropertyByKey("weare.db.username");
    private static final String password = Utils.getConfigPropertyByKey("weare.db.password");
    protected static final String deleteQuery =  "DELETE FROM %s WHERE %s=%s;";
    protected static final String findQuery = "SELECT * FROM %s WHERE %s=%s;";

    /**
     * Executes the provided SQL query and returns the result set.
     *
     * @param query the SQL query to be executed
     * @return the result set from the executed query
     */
    protected static ResultSet executeQuery(String query) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try(Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery();
            resultSet.first();
            return resultSet;
        } catch (SQLException e) {
            Utils.LOGGER.info(e);
        }
        return null;
    }

    /**
     * Retrieves an entity from the database based on the provided table, key, and value.
     *
     * @param table the table name
     * @param key the key to search for
     * @param value the value corresponding to the key
     * @return the result set containing the entity
     */
    public static ResultSet getEntity(String table, String key, String value) {
        return executeQuery(String.format(findQuery, table, key, value));
    }

    /**
     * Checks if the entity exists in the provided result set.
     *
     * @param resultSet the result set to check
     * @return true if the entity exists; otherwise, false
     */
    public static boolean entityExists(ResultSet resultSet) {
        try {
            return resultSet.getRow()==1;
        } catch (SQLException e) {
            Utils.LOGGER.info("Unsuccessful query.");
            Utils.LOGGER.info(e);
        }
        return false;
    }

    /**
     * Retrieves the ID of the entity from the result set based on the specified column label.
     *
     * @param resultSet the result set containing the entity
     * @param columnLabel the column label for the ID
     * @return the ID of the entity
     */
    public static int getEntityId(ResultSet resultSet, String columnLabel) {
        try {
            return resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteEntity(String table, String key, String value) {
        deleteEntity("entity", table, key, value);
    }

    public static void deleteEntity(String entityName, String table, String key, String value) {
        if (entityExists(getEntity(table, key, value))) {
            executeQuery(String.format(deleteQuery, table, key, value));
            if (!entityExists(getEntity(table, key, value))) Utils.LOGGER.info(String.format("Successfully deleted %s.", entityName));
            else Utils.LOGGER.info(String.format("Deleting %s was unsuccessful.", entityName));
        }
        else Utils.LOGGER.info(String.format("No %s found.", entityName));
    }

    public static int getEntityIdByKey(String table, String key, String value, String idColumnLabel) {
        return getEntityIdByKey("entity", table, key, value, idColumnLabel);
    }

    public static int getEntityIdByKey(String entityName, String table, String key, String value, String idColumnLabel) {
        ResultSet resultSet = getEntity(table, key, value);

        if (!entityExists(resultSet)) Utils.LOGGER.info(String.format("No %s found.", entityName));
        else return getEntityId(resultSet, idColumnLabel);
        return -1;
    }


}
