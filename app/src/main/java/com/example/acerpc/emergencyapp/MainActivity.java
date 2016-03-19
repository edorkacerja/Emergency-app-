package com.example.acerpc.emergencyapp;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                        Toast.makeText(MainActivity.this, "Emergency message sent", Toast.LENGTH_SHORT).show();


                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                        sendIntent.putExtra("sms_body", "default content");
                        sendIntent.setType("vnd.android-dir/mms-sms");
                        startActivity(sendIntent);

                        return true;
                    }
                });




        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);






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

                        Intent intent = new Intent(getBaseContext(), EmergencyContact.class);
                        startActivity(intent);
                        break;
                    // TODO: Other cases
                    case R.id.action_info:
                        Intent intent2 = new Intent(getBaseContext(), Info.class);
                        startActivity(intent2);
                        break;
                    case R.id.action_map:
                        Intent intent3 = new Intent(getBaseContext(), CampusMap.class);
                        startActivity(intent3);
                    break;
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

                Intent i = new Intent(this, CampusMap.class);
                startActivity(i);
                break;
        }

        return true;
    }










}
