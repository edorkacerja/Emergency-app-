package com.example.acerpc.emergencyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity   {

    Button btnReport;
    Button btnMap;
    Button btnEmergency;

    private Toolbar topToolbar;
    private Toolbar bottomToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbars();

        btnReport = (Button) findViewById(R.id.btnReport);
        btnMap = (Button) findViewById(R.id.btnMap);
        btnEmergency = (Button) findViewById(R.id.btnEmergency);


        btnEmergency.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Log.v("edor", "longclick");
                        return true;
                    }
                });

    }




    private void initToolbars() {
        Toolbar topToolbar = (Toolbar) findViewById(R.id.toolbar_top);
        setSupportActionBar(topToolbar);

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


    public boolean btnHomeClick(View view){
        switch (view.getId()){
            case (R.id.btnReport):

                Intent intent = new Intent(this, Report.class);
                startActivity(intent);
                Log.v("edor", "button report clicked");
                break;
            case R.id.btnMap:

                break;
        }

        return true;
    }














//    @Override
//    public boolean onLongClick(View v) {
//
//        if(v.getId()== R.id.btnEmergency){
//            Toast.makeText(this, "Long Clicked", Toast.LENGTH_SHORT).show();
//            Log.v("edor", "longclick");
//        }
//
//
//        return true;
//    }
}
