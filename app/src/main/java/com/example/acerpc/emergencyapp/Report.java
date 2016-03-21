package com.example.acerpc.emergencyapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Report extends AppCompatActivity implements OnItemClickListener{

    ListView listView;
    String[] reportTitles;
    int[] icons = {R.drawable.threat, R.drawable.weapon, R.drawable.robbery, R.drawable.threat};
    TextView reason;
    String reasonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initToolbars();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);


        Resources res = getResources();
        reportTitles = res.getStringArray(R.array.titles);
        reason = (TextView) findViewById(R.id.textView);
        //reasonString = (String) reason.getText();
        EdorsAdapter adapter = new EdorsAdapter(this, reportTitles, icons);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String s =(String) ((TextView) view.findViewById(R.id.textView)).getText();

        Intent intent = new Intent(this, ReportSheet.class);
        intent.putExtra("reason", s);
        startActivity(intent);



        Log.v("edor", "item "+ position +" clicked");
    }


    class EdorsAdapter extends ArrayAdapter<String> {

        Context context;
        int row;
        int[] imageIcons;
        String[] myTitles;

        EdorsAdapter(Context c, String[] titles, int[] imgs) {
            super(c, R.layout.single_row, R.id.textView, titles);
            this.context = c;
            this.row = R.layout.single_row;
            this.imageIcons = imgs;
            this.myTitles = titles;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowObject = inflater.inflate(row, parent, false);

            ImageView myImage = (ImageView) rowObject.findViewById(R.id.imageView);
            TextView myText = (TextView) rowObject.findViewById(R.id.textView);


            myImage.setImageResource(imageIcons[position]);
            myText.setText(myTitles[position]);


            return rowObject;
        }
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
