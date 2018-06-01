package com.shobhit.workshop.Models;

public class Workshop_Model {

    public static final String TABLE_NAME="workshops";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_DESCRIPTION="description";
    public static final String COLUMN_TIMESTAMP="timestamp";
    public static final String COLUMN_APPLIED="applied";
    public static final String COLUMN_IMAGEURL="imageURL";

    private int id;
    private String title;
    private String description;
    private String timestamp;
    private String applied;
    private String imageUrl;


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_IMAGEURL + " TEXT,"
                    + COLUMN_APPLIED + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";


    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getApplied() {
        return applied;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setApplied(String applied) {
        this.applied = applied;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
