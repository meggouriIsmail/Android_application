package com.meggouri.controlegestionclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayAfficher(View v){
        Intent i=new Intent(this,Afficher.class);
        startActivity(i);
    }

    public void displayAjouter(View v){
        Intent i=new Intent(this,Ajouter.class);
        startActivity(i);
    }

    public void displayModifier(View v){
        Intent i=new Intent(this,Modifier.class);
        startActivity(i);
    }

    public void displaySupprimer(View v){
        Intent i=new Intent(this,Supprimer.class);
        startActivity(i);
    }
}
