package com.testframework.databasehelper;

import java.sql.ResultSet;

public class PostHelper extends DatabaseHelper{

    private static final String tableName = "posts_table";

    public static ResultSet getPost(String key, String value) {
        return getEntity(tableName, key, value);
    }

    public static int getPostId(ResultSet resultSet) {
        return getEntityId(resultSet, "post_id");
    }

    public static void deletePost(String key, String value) {
        deleteEntity("post", tableName, key, value);
    }

    public static int getPostByContent(String content) {
        return getEntityIdByKey("post", tableName, "content", content, "post_id");
    }

}
