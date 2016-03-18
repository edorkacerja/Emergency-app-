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
            Intent intent = new Intent(this, SkaptoparaCampus.class);
            startActivity(intent);
        }


        return true;
    }




    private void initToolbars() {

        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.toolbar_bottom);
        bottomToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_call:
                        // TODO
                        break;
                    // TODO: Other cases
                }
                return true;
            }
        });
        // Inflate a menu to be displayed in the toolbar
        bottomToolbar.inflateMenu(R.menu.menu_main);
    }
}
