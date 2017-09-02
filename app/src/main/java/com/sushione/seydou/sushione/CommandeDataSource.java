package com.sushione.seydou.sushione;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seydou on 10/08/17.
 */


public class CommandeDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_NUMBER, MySQLiteHelper.COLUMN_CHOIX};

    public CommandeDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Repas createCommande(String choix) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CHOIX, choix);
        values.put(MySQLiteHelper.COLUMN_NUMBER,1);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMANDE, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMANDE,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Repas newComment = cursorToRepas(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteRepas(Repas repas) {
        long id = repas.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMANDE, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Repas> getAllCommand() {
        List<Repas> repass = new ArrayList<Repas>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMANDE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Repas repas = cursorToRepas(cursor);
            repass.add(repas);
            cursor.moveToNext();
        }
        cursor.close();
        return repass;
    }
    private Repas cursorToRepas(Cursor cursor) {
        Repas repas = new Repas();
        repas.setId(cursor.getLong(0));
        repas.setNombre(cursor.getInt(1));
        repas.setChoix(cursor.getString(2));
        return repas;
    }

}
