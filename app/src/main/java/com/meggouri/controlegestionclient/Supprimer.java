package com.meggouri.controlegestionclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Supprimer extends AppCompatActivity {

    Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);

        helper = new Helper(this);
    }

    public void supprimer(View v) {
        EditText selectedID = (EditText) findViewById(R.id.idSupp);
        helper.deleteEnregistrement(Integer.parseInt(selectedID.getText().toString()));
        alertMessage("Supprimer avec succé");
    }

    public void alertMessage(String msg){

        AlertDialog alertDialog = new AlertDialog.Builder(Supprimer.this).create();
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
