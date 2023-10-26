package com.testframework.databasehelper;

/**
 * A utility class for managing connection-related database operations, extending DatabaseHelper.
 *
 * @see DatabaseHelper
 */
public class ConnectionHelper extends DatabaseHelper {

    private static String truncateConnectionsTableQuery = "TRUNCATE TABLE connections_table;";

    /**
     * Truncates the requests table in the database.
     */
    public static void truncateConnectionsTable() {
        DatabaseHelper.executeQuery(truncateConnectionsTableQuery);
    }
}
