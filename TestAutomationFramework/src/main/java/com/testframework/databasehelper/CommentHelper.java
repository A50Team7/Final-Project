package com.testframework.databasehelper;

import java.sql.ResultSet;

/**
 * A utility class for managing comment-related database operations, extending DatabaseHelper.
 *
 * @see DatabaseHelper
 */
public class CommentHelper extends DatabaseHelper {

    private static final String tableName = "comments_table";

    /**
     * Retrieves a comment based on the specified key and value.
     *
     * @param key   the key to search for
     * @param value the value corresponding to the key
     * @return the result set containing the comment
     */
    public static ResultSet getComment(String key, String value) {
        return getEntity(tableName, key, value);
    }

    /**
     * Retrieves the comment ID from the result set.
     *
     * @param resultSet the result set containing the comment
     * @return the ID of the comment
     */
    public static int getCommentId(ResultSet resultSet) {
        return getEntityId(resultSet, "comment_id");
    }

    /**
     * Deletes a comment based on the specified key and value.
     *
     * @param key   the key to search for
     * @param value the value corresponding to the key
     */
    public static void deleteComment(String key, String value) {
        deleteEntity("comment", tableName, key, value);
    }

}
