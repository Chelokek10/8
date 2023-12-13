package com.example.writter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// Клас-допоміжник для роботи з базою даних SQLite
public class FlowerDatabaseHelper extends SQLiteOpenHelper {
    // Назва таблиці та її колонки
    public static final String TABLE_NAME = "flowers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String TAG = "FlowerDatabaseHelper";
    private static final String DATABASE_NAME = "FlowerDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    // Конструктор класу, приймає контекст застосунку
    public FlowerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Метод, який викликається при створенні бази даних
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    // Метод, який викликається при оновленні бази даних
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Метод для копіювання бази даних з папки assets
    protected void copyDatabaseFromAssets() {
        String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();

        try (InputStream inputStream = context.getAssets().open(DATABASE_NAME);
             OutputStream outputStream = new FileOutputStream(dbPath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            Log.i(TAG, "Database copied successfully");

        } catch (IOException e) {
            Log.e(TAG, "Error copying database", e);
        }
    }

    // Метод для копіювання бази даних (публічний інтерфейс)
    public void copyDatabase() {
        copyDatabaseFromAssets();
    }

    // Метод для відкриття бази даних для читання або запису
    public void openDatabase() {
        getWritableDatabase();
    }

    // Метод для закриття бази даних
    @Override
    public synchronized void close() {
        super.close();
    }
}
