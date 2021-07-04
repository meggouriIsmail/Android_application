package com.meggouri.controlegestionclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.Date;

public class Helper extends SQLiteOpenHelper {
    public final static String nom_bd="GClients.db";
    public final static int version_bd=1;
    public final static String nom_table="employe";
    private static final String TAG = "Helper";

    //SQLiteDatabase db;

    public static final class SharedInformation implements BaseColumns{
        public static final String id_client="idcli";
        public final static String nom_client="nom";
        public final static String prenom_client="prenom";
        public final static String daten_client="daten";
        public final static String sexe_client="sexe";
        public final static String situationf_client="situationf";
        public final static String diplome_client="categoriep";
        public final static String salaire_client="salaire";



        private SharedInformation(){}
    }

    public Helper(Context context){
        super(context,nom_bd,null,version_bd);
    }

    @Override
    public void onCreate(SQLiteDatabase bd){
        bd.execSQL("create table " + nom_table + "(" + SharedInformation.id_client +" integer primary key autoincrement," +
                SharedInformation.nom_client + " varchar(50)," +
                SharedInformation.prenom_client + " varchar(50)," +
                SharedInformation.daten_client + " Date," +
                SharedInformation.sexe_client + " varchar(50)," +
                SharedInformation.situationf_client + " varchar(50)," +
                SharedInformation.diplome_client + " varchar(50)," +
                SharedInformation.salaire_client + " FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd,int versionAncienne,int versionNouvelle){
        bd.execSQL("drop table " + nom_table);
        onCreate(bd);
    }

    public void updateEnregistrement(String nom, int id, String prenom, String stFam, String dp, float salary, String sex, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SharedInformation.nom_client, nom);
        values.put(SharedInformation.prenom_client, prenom);
        values.put(SharedInformation.situationf_client, stFam);
        values.put(SharedInformation.diplome_client, dp);
        values.put(SharedInformation.daten_client, String.valueOf(date));
        values.put(SharedInformation.sexe_client, sex);
        values.put(SharedInformation.salaire_client, salary);
        db.update(nom_table, values,SharedInformation.id_client + " = ? ",new String[]{ String.valueOf(id) });
    }

    public void deleteEnregistrement(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + nom_table + " WHERE "
                + SharedInformation.id_client + " = '" + id + "'";
        Log.d(TAG, "deleteName: query: " + query);
        db.execSQL(query);
    }

}
