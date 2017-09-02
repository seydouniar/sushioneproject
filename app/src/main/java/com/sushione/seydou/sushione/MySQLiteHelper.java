package com.sushione.seydou.sushione;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by seydou on 10/08/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COMMANDE = "commandes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NUMBER = "nombre";
    public static final String COLUMN_CHOIX = "choix";

    private static final String DATABASE_NAME = "commandes.db";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMANDE + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NUMBER
            + " integer, "+COLUMN_CHOIX + " text not null);";


    public MySQLiteHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        if (oldVersion<2)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMANDE);
        onCreate(db);
    }
}
