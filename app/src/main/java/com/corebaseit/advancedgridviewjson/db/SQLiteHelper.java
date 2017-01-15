package com.corebaseit.advancedgridviewjson.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.corebaseit.advancedgridviewjson.models.FavoritesListModel;
import com.corebaseit.advancedgridviewjson.models.AnotherListModel;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String LOGTAG = "GRIDVIEW";

    private static final String DATABASE_NAME = "gridviewjson.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Table A
        {
            String sql = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT );",
                    FavoritesListModel.Entry.TABLE_NAME,
                    FavoritesListModel.Entry.COLUMN_ID,
                    FavoritesListModel.Entry.COLUMN_TITLE
            );

            db.execSQL(sql);
        }

        //Table B
        {
            String sql = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT );",
                    AnotherListModel.Entry.TABLE_NAME,
                    AnotherListModel.Entry.COLUMN_ID,
                    AnotherListModel.Entry.COLUMN_TITLE,
                    AnotherListModel.Entry.COLUMN_URL,
                    AnotherListModel.Entry.COLUMN_PIC,
                    AnotherListModel.Entry.COLUMN_DESC
            );

            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*
        db.execSQL("DROP TABLE IF EXISTS" +  TABLE_SEARCH);
        onCreate(db);
*/
//        Log.i(LOGTAG, "Database has been upgraded from " + oldVersion + " to " + newVersion);

    }

}
