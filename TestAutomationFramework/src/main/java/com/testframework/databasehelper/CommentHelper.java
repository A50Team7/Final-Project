package com.testframework.databasehelper;

import java.sql.ResultSet;

public class CommentHelper extends DatabaseHelper{

    private static final String tableName = "comments_table";

    public static ResultSet getComment(String key, String value) {
        return getEntity(tableName, key, value);
    }

    public static int getCommentId(ResultSet resultSet) {
        return getEntityId(resultSet, "comment_id");
    }

    public static void deleteComment(String key, String value) {
        deleteEntity("comment", tableName, key, value);
    }

}
