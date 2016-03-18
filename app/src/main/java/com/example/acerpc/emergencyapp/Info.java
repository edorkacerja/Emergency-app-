package com.example.acerpc.emergencyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initToolbars();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
