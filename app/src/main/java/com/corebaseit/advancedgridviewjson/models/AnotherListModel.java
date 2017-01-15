package com.corebaseit.advancedgridviewjson.models;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class AnotherListModel {

    private String url, name, picture, description;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AnotherListModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static abstract class Entry{

        public static final String TABLE_NAME = "wishlist";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_TITLE = "name";
        public static final String COLUMN_PIC = "picture";
        public static final String COLUMN_DESC = "description";

    }


}
