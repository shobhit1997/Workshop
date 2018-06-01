package com.shobhit.workshop.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.shobhit.workshop.Models.Workshop_Model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "workshop_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Workshop_Model.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Workshop_Model.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertWorkshop(Workshop_Model workshop_model) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Workshop_Model.COLUMN_TITLE,workshop_model.getTitle());
        values.put(Workshop_Model.COLUMN_DESCRIPTION,workshop_model.getDescription());
        values.put(Workshop_Model.COLUMN_IMAGEURL,workshop_model.getImageUrl());
        values.put(Workshop_Model.COLUMN_APPLIED,workshop_model.getApplied());
        values.put(Workshop_Model.COLUMN_TIMESTAMP,workshop_model.getTimestamp());

        // insert row
        long id = db.insert(Workshop_Model.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Workshop_Model getWorkshop(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Workshop_Model.TABLE_NAME,
                new String[]{Workshop_Model.COLUMN_ID, Workshop_Model.COLUMN_TITLE,Workshop_Model.COLUMN_DESCRIPTION,Workshop_Model.COLUMN_IMAGEURL,Workshop_Model.COLUMN_APPLIED, Workshop_Model.COLUMN_TIMESTAMP},
                Workshop_Model.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Workshop_Model workshop = new Workshop_Model();
        workshop.setId(cursor.getInt(cursor.getColumnIndex(Workshop_Model.COLUMN_ID)));
        workshop.setTitle(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TITLE)));
        workshop.setDescription(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_DESCRIPTION)));
        workshop.setImageUrl(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_IMAGEURL)));
        workshop.setApplied(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_APPLIED)));
        workshop.setTimestamp(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TIMESTAMP)));
        // close the db connection
        cursor.close();

        return workshop;
    }

    public List<Workshop_Model> getAllWorkshops() {
        List<Workshop_Model> workshops = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Workshop_Model.TABLE_NAME + " ORDER BY " +
                Workshop_Model.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Workshop_Model workshop = new Workshop_Model();
                workshop.setId(cursor.getInt(cursor.getColumnIndex(Workshop_Model.COLUMN_ID)));
                workshop.setTitle(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TITLE)));
                workshop.setDescription(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_DESCRIPTION)));
                workshop.setImageUrl(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_IMAGEURL)));
                workshop.setApplied(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_APPLIED)));
                workshop.setTimestamp(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TIMESTAMP)));

                workshops.add(workshop);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return workshops;
    }


    public List<Workshop_Model> getAppliedWorkshops() {
        List<Workshop_Model> workshops = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Workshop_Model.TABLE_NAME,
                new String[]{Workshop_Model.COLUMN_ID, Workshop_Model.COLUMN_TITLE,Workshop_Model.COLUMN_DESCRIPTION,Workshop_Model.COLUMN_IMAGEURL,Workshop_Model.COLUMN_APPLIED, Workshop_Model.COLUMN_TIMESTAMP},
                Workshop_Model.COLUMN_APPLIED + "=?",
                new String[]{"applied"}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Workshop_Model workshop = new Workshop_Model();
                workshop.setId(cursor.getInt(cursor.getColumnIndex(Workshop_Model.COLUMN_ID)));
                workshop.setTitle(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TITLE)));
                workshop.setDescription(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_DESCRIPTION)));
                workshop.setImageUrl(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_IMAGEURL)));
                workshop.setApplied(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_APPLIED)));
                workshop.setTimestamp(cursor.getString(cursor.getColumnIndex(Workshop_Model.COLUMN_TIMESTAMP)));

                workshops.add(workshop);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return workshops;
    }


    public int getWorkshopsCount() {
        String countQuery = "SELECT  * FROM " + Workshop_Model.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateWorkshop(Workshop_Model workshop_model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Workshop_Model.COLUMN_APPLIED, workshop_model.getApplied());

        // updating row
        return db.update(Workshop_Model.TABLE_NAME, values, workshop_model.COLUMN_ID + " = ?",
                new String[]{String.valueOf(workshop_model.getId())});
    }

    public void deleteWorkshop(Workshop_Model workshop_model) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Workshop_Model.TABLE_NAME, Workshop_Model.COLUMN_ID + " = ?",
                new String[]{String.valueOf(workshop_model.getId())});
        db.close();
    }
}
