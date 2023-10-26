package com.testframework.databasehelper;

/**
 * A utility class for managing requests-related database operations, extending DatabaseHelper.
 *
 * @see DatabaseHelper
 */
public class RequestsHelper extends DatabaseHelper {

    private static String truncateRequestsTableQuery = "TRUNCATE TABLE requests;";

    /**
     * Truncates the requests table in the database.
     */
    public static void truncateRequestsTable() {
        DatabaseHelper.executeQuery(truncateRequestsTableQuery);
    }

}
