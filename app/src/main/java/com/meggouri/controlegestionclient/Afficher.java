package com.meggouri.controlegestionclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Afficher extends AppCompatActivity {

    SQLiteDatabase db;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher);

        helper = new Helper(this);
        opendb();
        String columns[] = new String[]{Helper.SharedInformation.id_client,Helper.SharedInformation.nom_client,Helper.SharedInformation.prenom_client,Helper.SharedInformation.daten_client,
                Helper.SharedInformation.sexe_client,Helper.SharedInformation.situationf_client,Helper.SharedInformation.diplome_client,Helper.SharedInformation.salaire_client};
        Cursor cur = db.query(Helper.nom_table, columns, null,null,null,null,null);

        ArrayList<String> list = new ArrayList<>();

        if (cur.moveToFirst()) {
            String enrg = null;
            do {
                enrg = cur.getString(cur.getColumnIndex(Helper.SharedInformation.id_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.nom_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.prenom_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.daten_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.sexe_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.situationf_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.diplome_client)) + "\n" +
                        cur.getString(cur.getColumnIndex(Helper.SharedInformation.salaire_client)) + "\n\n\n";
                list.add(enrg);

            } while (cur.moveToNext());

            closedb();

            ListView vue = (ListView) findViewById(R.id.listView);
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
            vue.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        opendb();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closedb();
    }

    private void opendb() throws SQLiteException {
        try{
            db = helper.getWritableDatabase();
        }
        catch(SQLiteException ex){
            db = helper.getReadableDatabase();

        }
    }

    private void closedb(){
        db.close();
    }

}
