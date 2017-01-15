package com.corebaseit.advancedgridviewjson.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.corebaseit.advancedgridviewjson.models.FavoritesListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent Bevia on 27/10/16. <br />
 * vbevia@ieee.org
 */

public class FavoritesListFragmentHelper {

	public static final String LOGTAG = "GRIDVIEW";

	private SQLiteOpenHelper dbhelper;

	public FavoritesListFragmentHelper(Context context) {
		dbhelper = new SQLiteHelper(context);
		
	}

	public FavoritesListFragmentHelper open() throws SQLException {
		Log.i(LOGTAG, "Database opened");
//		database = dbhelper.getWritableDatabase();
		return this;
	}

	public void close() throws SQLException {
		Log.i(LOGTAG, "Database closed");
		dbhelper.close();
		
	}

	public FavoritesListModel create(FavoritesListModel search)
	{
		ContentValues values = new ContentValues();

		values.put(FavoritesListModel.Entry.COLUMN_TITLE, search.getTitle());
		
		//now lets insert the row into the database:
        SQLiteDatabase database = dbhelper.getWritableDatabase();;
		long insertid = database.insert(FavoritesListModel.Entry.TABLE_NAME, null, values);
		search.setId(insertid);
        database.close();
		return search;

	}
	//I'll create a list of search objects to retrive all of my columns and rows from the db:
	public List<FavoritesListModel> findAll() {

        List<FavoritesListModel> search = new ArrayList<>();

		//next we'll query the db:
        String[] allColumns = {
                FavoritesListModel.Entry.COLUMN_ID,
                FavoritesListModel.Entry.COLUMN_TITLE
        };

        SQLiteDatabase database = dbhelper.getReadableDatabase();
		Cursor cursor = database.query(
                FavoritesListModel.Entry.TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                "_id DESC",
                "10"
        );
		
//		Log.i(LOGTAG, "return" + cursor.getCount() + " rows");

		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				FavoritesListModel newSearch = new FavoritesListModel();
				newSearch.setId(cursor.getLong(cursor.getColumnIndex(FavoritesListModel.Entry.COLUMN_ID)));
				newSearch.setTitle(cursor.getString(cursor.getColumnIndex(FavoritesListModel.Entry.COLUMN_TITLE)));
				search.add(newSearch);
			}
		}
        database.close();
		return search;
	}

    public void deleteRow(int id) {

        String[] whereArgs = { String.valueOf(id) };

        SQLiteDatabase database = dbhelper.getWritableDatabase();
        database.delete(
                FavoritesListModel.Entry.TABLE_NAME,
                "_id=?",
                whereArgs
        );
        database.close();
    }

    public void deleteRow(String name) {

        String[] whereArgs = { name };

        SQLiteDatabase database = dbhelper.getWritableDatabase();
        database.delete(
                FavoritesListModel.Entry.TABLE_NAME,
                FavoritesListModel.Entry.COLUMN_TITLE + "=?",
                whereArgs
        );
        database.close();
    }


}
