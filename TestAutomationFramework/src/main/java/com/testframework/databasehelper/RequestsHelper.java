package com.testframework.databasehelper;

public class RequestsHelper extends DatabaseHelper{

    private static String truncateRequestsTableQuery = "TRUNCATE TABLE requests;";

    public static void truncateRequestsTable() {
        DatabaseHelper.executeQuery(truncateRequestsTableQuery);
    }

}
