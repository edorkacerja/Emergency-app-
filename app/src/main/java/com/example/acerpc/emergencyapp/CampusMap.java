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


        return true;
    }

    private void initToolbars() {
        Toolbar topToolbar = (Toolbar) findViewById(R.id.toolbar_top);
//        setSupportActionBar(topToolbar);

        Toolbar bottomToolbar = (Toolbar) findViewById(R.id.toolbar_bottom);
        bottomToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_call:

                        Intent intent = new Intent(getBaseContext(), EmergencyContact.class);
                        startActivity(intent);
                        break;
                    // TODO: Other cases
                    case R.id.action_info:
                        Intent intent2 = new Intent(getBaseContext(), Info.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_map:
//                        Intent intent3 = new Intent(getBaseContext(), CampusMap.class);
//                        startActivity(intent3);
                        break;
                }
                return true;
            }
        });
        // Inflate a menu to be displayed in the toolbar
        bottomToolbar.inflateMenu(R.menu.menu_main);
    }
}
