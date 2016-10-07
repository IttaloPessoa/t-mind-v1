package br.com.ufpb.ittalopessoa.t_mind.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "T-Mind";
    private static final int DB_VERSION = 1;

    private DBCode dbCode;

    public DBCore(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        dbCode = new DBCode();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TAG", "Entrou para criar o DB");
        for (String s : dbCode.createTables()){
            db.execSQL("CREATE TABLE " + s);
        }
        for(String s : dbCode.createInserts()){
            db.execSQL("INSERT INTO " + s);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE usuario");
        db.execSQL("DROP TABLE pergunta");
        db.execSQL("DROP TABLE resposta");
        onCreate(db);
    }
}
