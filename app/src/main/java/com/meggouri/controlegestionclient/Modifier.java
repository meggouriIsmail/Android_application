package com.meggouri.controlegestionclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class Modifier extends AppCompatActivity {

    Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        helper = new Helper(this);
    }

    public void modifier(View view) {

        EditText idcli = (EditText) findViewById(R.id.id);
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

        if(!idcli.getText().toString().equals("")){
            helper.updateEnregistrement(nom.getText().toString(),Integer.parseInt(idcli.getText().toString())
                    ,prenom.getText().toString(),sitationf.getSelectedItem().toString(),
                    diplome.getSelectedItem().toString(),Float.parseFloat(salaire.getText().toString()),s,datn);
            alertMessage("Modifier avec succé");
        }else{
            alertMessage("Vous dever entrer un identifiant");
        }
    }

    public void alertMessage(String msg){

        AlertDialog alertDialog = new AlertDialog.Builder(Modifier.this).create();
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
}
