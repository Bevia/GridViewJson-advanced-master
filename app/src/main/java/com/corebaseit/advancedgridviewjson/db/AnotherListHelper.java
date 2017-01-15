package com.corebaseit.advancedgridviewjson.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.corebaseit.advancedgridviewjson.models.AnotherListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class AnotherListHelper extends SQLiteHelper {

    public AnotherListHelper(Context context) {
        super(context);
    }


    public List<AnotherListModel> findAll() {

        List<AnotherListModel> result = new ArrayList<>();

        //next we'll query the db:
        String[] allColumns = {
                AnotherListModel.Entry.COLUMN_ID,
                AnotherListModel.Entry.COLUMN_TITLE,
                AnotherListModel.Entry.COLUMN_URL,
                AnotherListModel.Entry.COLUMN_PIC,
                AnotherListModel.Entry.COLUMN_DESC

        };

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(
                AnotherListModel.Entry.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                "_id DESC"
        );

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                AnotherListModel model = new AnotherListModel();
                model.setId(cursor.getLong(cursor.getColumnIndex(AnotherListModel.Entry.COLUMN_ID)));
                model.setName(cursor.getString(cursor.getColumnIndex(AnotherListModel.Entry.COLUMN_TITLE)));
                model.setUrl(cursor.getString(cursor.getColumnIndex(AnotherListModel.Entry.COLUMN_URL)));
                model.setPicture(cursor.getString(cursor.getColumnIndex(AnotherListModel.Entry.COLUMN_PIC)));
                model.setDescription(cursor.getString(cursor.getColumnIndex(AnotherListModel.Entry.COLUMN_DESC)));
                result.add(model);
            }
        }
        database.close();
        return result;
    }

    public AnotherListModel create(AnotherListModel model)
    {
        ContentValues values = new ContentValues();

        values.put(AnotherListModel.Entry.COLUMN_URL, model.getUrl());
        values.put(AnotherListModel.Entry.COLUMN_TITLE, model.getName());
        values.put(AnotherListModel.Entry.COLUMN_PIC, model.getPicture());
        values.put(AnotherListModel.Entry.COLUMN_DESC, model.getDescription());

        SQLiteDatabase database = this.getWritableDatabase();;
        long insertid = database.insert(AnotherListModel.Entry.TABLE_NAME, null, values);
        model.setId(insertid);
        database.close();
        return model;

    }

    public void deleteRow(int id) {

        String[] whereArgs = { String.valueOf(id) };

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(
                AnotherListModel.Entry.TABLE_NAME,
                "_id=?",
                whereArgs
        );
        database.close();
    }

    public void deleteRow(String url) {

        String[] whereArgs = { url };

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(
                AnotherListModel.Entry.TABLE_NAME,
                AnotherListModel.Entry.COLUMN_URL + "=?",
                whereArgs
        );
        database.close();
    }


}
