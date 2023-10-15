package com.testframework.databasehelper;

import com.testframework.FormatHelper;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PostHelper extends DatabaseHelper{

    private static final String tableName = "posts_table";
    private static final String tableSpecificQuery =
            "SELECT * FROM " + tableName + " WHERE content LIKE '%s' AND date_time LIKE '%s';";

    public static ResultSet getPost(String key, String value) {
        return getEntity(tableName, key, value);
    }

    public static int getPostId(ResultSet resultSet) {
        return getEntityId(resultSet, "post_id");
    }

    public static void deletePost(String key, String value) {
        deleteEntity("post", tableName, key, value);
    }

}
