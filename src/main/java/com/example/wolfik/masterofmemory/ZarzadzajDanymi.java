package com.example.wolfik.masterofmemory;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Bundle;
        import android.util.Log;
        import android.database.Cursor;

/**
 * Created by Wolfik on 23.01.2018.
 */

public class ZarzadzajDanymi {
    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "nickname";
    public static final String TABLE_ROW_SCORE = "score";

    private static final String DB_NAME = "master_of_memory_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_S = "scores";

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper
    {
        public CustomSQLiteOpenHelper(Context context)
        {
            super(context,DB_NAME,null,DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            String newTableQueryString = "create table "
                    + TABLE_N_AND_S + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_SCORE
                    + " text not null);";

            db.execSQL(newTableQueryString);
            Log.i("Dzialam kurwa","No dzialam");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }

    public ZarzadzajDanymi(Context context)
    {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(String nickname, String score)
    {
        String query = "INSERT INTO " + TABLE_N_AND_S + " (" +
                TABLE_ROW_NAME + ", " + TABLE_ROW_SCORE + ") " +
                "VALUES (" +
                "'" + nickname + "'" + ", " + "'" + score + "'" + ");";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }
    public void delete(String nickname)
    {
        String query = "DELETE FROM " + TABLE_N_AND_S +
                " WHERE " + TABLE_ROW_NAME +
                " = '" + nickname + "';";
        Log.i("delete() = ", query);
        db.execSQL(query);
    }
    public Cursor selectAll()
    {
        Cursor c = db.rawQuery("SELECT *" + " from " + TABLE_N_AND_S, null);
        return c;
    }
    public Cursor searchName(String name)
    {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_NAME +
                ", " + TABLE_ROW_SCORE +
                " from " + TABLE_N_AND_S + " WHERE " +
                TABLE_ROW_NAME + " = " + name + "';";
        Log.i("searchName() = ",query);
        Cursor c = db.rawQuery(query,null);
        return c;
    }
}
