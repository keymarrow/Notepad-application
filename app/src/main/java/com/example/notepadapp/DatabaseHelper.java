package com.example.notepadapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_SUBTITLE = "subtitle";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NOTES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SUBTITLE + " TEXT," +
                COLUMN_BODY + " TEXT" +
                ")";
        try {
            db.execSQL(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NOTES;
        try {
            db.execSQL(dropTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(db);
    }


    public void addNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getTitle());
        values.put(COLUMN_SUBTITLE, note.getSubtitle());
        values.put(COLUMN_BODY, note.getBody());
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public List<Note> getAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES, null);
        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_ID);
                int titleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
                int subtitleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_SUBTITLE);
                int bodyIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_BODY);

                int id = cursor.getInt(idIndex);
                String title = cursor.getString(titleIndex);
                String subtitle = cursor.getString(subtitleIndex);
                String body = cursor.getString(bodyIndex);

                Note note = new Note(title, subtitle, body);
                note.setId(id);

                noteList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }


    public int updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, note.getTitle());
        values.put(COLUMN_SUBTITLE, note.getSubtitle());
        values.put(COLUMN_BODY, note.getBody());

        int status = db.update(TABLE_NOTES, values, COLUMN_ID + "=?", new String[]{String.valueOf(note.getId())});
        db.close();
        return status;
    }


    public void deleteNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTES,COLUMN_ID+"=?",new String[]{String.valueOf(note.getId())});
    }
}
