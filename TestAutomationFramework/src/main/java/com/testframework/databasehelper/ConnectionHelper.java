package com.testframework.databasehelper;

public class ConnectionHelper extends DatabaseHelper{

    private static String truncateConnectionsTableQuery = "TRUNCATE TABLE connections_table;";

    public static void truncateConnectionsTable() {
        DatabaseHelper.executeQuery(truncateConnectionsTableQuery);
    }
}
