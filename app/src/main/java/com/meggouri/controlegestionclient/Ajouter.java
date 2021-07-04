package com.meggouri.controlegestionclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ajouter extends AppCompatActivity {

    SQLiteDatabase db;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        helper = new Helper(this);
    }
    public void ajouter(View v) {
        //EditText idcli = (EditText) findViewById(R.id.id);
        EditText nom = (EditText) findViewById(R.id.nom);
        EditText prenom = (EditText) findViewById(R.id.prenom);
        DatePicker daten = (DatePicker) findViewById(R.id.daten);
        RadioGroup sexe = (RadioGroup) findViewById(R.id.myRadioGroup);
        Spinner sitationf = (Spinner) findViewById(R.id.situationf);
        Spinner diplome = (Spinner) findViewById(R.id.diplome);
        EditText salaire = (EditText) findViewById(R.id.salaire);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = (daten.getDayOfMonth() + "-" + (daten.getMonth() + 1) + "-" + daten.getYear()).toString();

        Date datn = formatter.parse(dateInString, new ParsePosition(0));

        String s;
        if(R.id.masculin == sexe.getCheckedRadioButtonId()){
            s = "Masculin";
        }
        else{
            s ="Féminin";
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(Helper.SharedInformation.nom_client, nom.getText().toString());
        contentValues.put(Helper.SharedInformation.prenom_client, prenom.getText().toString());
        contentValues.put(Helper.SharedInformation.daten_client, datn.toString());
        contentValues.put(Helper.SharedInformation.sexe_client, s);
        contentValues.put(Helper.SharedInformation.situationf_client, sitationf.getSelectedItem().toString());
        contentValues.put(Helper.SharedInformation.diplome_client, diplome.getSelectedItem().toString());
        contentValues.put(Helper.SharedInformation.salaire_client, Float.parseFloat(salaire.getText().toString()));

        opendb();

        long x = db.insert(Helper.nom_table,null,contentValues);

        closedb();

        alertMessage("Ajout réussi, x = " + x);

    }

    public void alertMessage(String msg){

        AlertDialog alertDialog = new AlertDialog.Builder(Ajouter.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
