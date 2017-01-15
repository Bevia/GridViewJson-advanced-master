package com.corebaseit.advancedgridviewjson.models;

import android.provider.BaseColumns;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class FavoritesListModel {

    private long id;
    private String title;
    private String extra_text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtra_text() {
        return extra_text;
    }

    public void setExtra_text(String extra_text) {
        this.extra_text = extra_text;
    }

    public static abstract class Entry implements BaseColumns {

        public static final String TABLE_NAME = "search";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TITLE = "title";

    }

}
