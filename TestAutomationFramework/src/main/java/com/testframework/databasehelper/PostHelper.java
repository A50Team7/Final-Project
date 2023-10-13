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

    public static int getPostByContent(String content) {
        return getEntityIdByKey("post", tableName, "content", content, "post_id");
    }

    public static ResultSet getPostsToDelete(String content, LocalDateTime dateTime) {
        return executeQuery(String.format(tableSpecificQuery, content, FormatHelper.formatDateTime(dateTime)));
    }

    public static ArrayList<Integer> getPostsToDeleteIds(String content, LocalDateTime dateTime) {
        ResultSet resultSet = getPostsToDelete(content, dateTime);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        {
            ids.add(getPostId(resultSet));
        } while(entityExists(resultSet));
        return ids;
    }

    public static void deleteMultiplePostsById(ArrayList<Integer> ids) {
        for (int id : ids) {
            deletePost("post_id", String.valueOf(id));
        }
    }

}
