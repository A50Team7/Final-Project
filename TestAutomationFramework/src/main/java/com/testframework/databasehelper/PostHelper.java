package com.testframework.databasehelper;

import java.sql.ResultSet;

/**
 * A utility class for managing post-related database operations, extending DatabaseHelper.
 *
 * @see DatabaseHelper
 */
public class PostHelper extends DatabaseHelper{

    private static final String tableName = "posts_table";
    private static final String tableSpecificQuery =
            "SELECT * FROM " + tableName + " WHERE content LIKE '%s' AND date_time LIKE '%s';";

    /**
     * Retrieves a post based on the specified key and value.
     *
     * @param key the key to search for
     * @param value the value corresponding to the key
     * @return the result set containing the post
     */
    public static ResultSet getPost(String key, String value) {
        return getEntity(tableName, key, value);
    }

    /**
     * Retrieves the post ID from the result set.
     *
     * @param resultSet the result set containing the post
     * @return the ID of the post
     */
    public static int getPostId(ResultSet resultSet) {
        return getEntityId(resultSet, "post_id");
    }

    /**
     * Deletes a post based on the specified key and value.
     *
     * @param key the key to search for
     * @param value the value corresponding to the key
     */
    public static void deletePost(String key, String value) {
        deleteEntity("post", tableName, key, value);
    }

}
