package com.example.acerpc.emergencyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class CampusMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initToolbars();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public boolean showCampusMap(View view){

        if(view.getId()==R.id.imageButton2) {
            Intent intent = new Intent(this, SkaptoparaCampus.class);
            startActivity(intent);
        }else if (view.getId()==R.id.imageButton3){
            Intent intent = new Intent(this, MainBuilding.class);
            startActivity(intent);
        }

        String locaton = "https://www.google.bg/maps/@" + 42.0165332 + "," + 23.084957 + ",16z?hl=en";

        return true;
    }


}
