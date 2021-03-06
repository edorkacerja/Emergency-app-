package com.example.acerpc.emergencyapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ReportSheet extends AppCompatActivity implements View.OnClickListener {

    private int PICK_IMAGE_REQUEST = 1;

    TextView fullNameTextV, addressTextV, buildingTextV, descriptionTextV, reasonReportSheet;
    Button btnSend, btnAttachment;
    String fullName, address, building, description, attachmentFile, reason, email;
    ImageView profilePic;
    Uri URI = null;

    private static final int PICK_FROM_GALLERY = 101;

    int columnIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_sheet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fullNameTextV = (TextView) findViewById(R.id.fullNameText);
        addressTextV = (TextView) findViewById(R.id.addressText);
        buildingTextV = (TextView) findViewById(R.id.buildingText);
        descriptionTextV = (TextView) findViewById(R.id.descriptionText);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnAttachment = (Button) findViewById(R.id.btnAttachment);
        profilePic = (ImageView) findViewById(R.id.profilePicture);


        reason = getIntent().getStringExtra("reason");
        initToolbars();


        btnSend.setOnClickListener(this);
        btnAttachment.setOnClickListener(this);


        reasonReportSheet = (TextView) findViewById(R.id.textView2);
        reasonReportSheet.setText(reason);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
//
//            //Get Path
//            Uri selectedImage = data.getData();
//
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//
//            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//
//            cursor.moveToFirst();
//
//            columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            attachmentFile = cursor.getString(columnIndex);
//            Log.e("Attachment Path:", attachmentFile);
//            URI = Uri.parse("file://" + attachmentFile);
//            cursor.close();
//        }
//    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnSend) {

            try {

                email = "edorkacerja@gmail.com";
                fullName = fullNameTextV.getText().toString();
                description = descriptionTextV.getText().toString();

                final Intent emailIntent = new Intent(
                        android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{email});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        fullName);
                if (URI != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, URI);
                }
                emailIntent
                        .putExtra(android.content.Intent.EXTRA_TEXT, description);
                this.startActivity(Intent.createChooser(emailIntent,
                        "Sending email..."));

            } catch (Throwable t) {
                Toast.makeText(this,

                        "Request failed try again: " + t.toString(),
                        Toast.LENGTH_LONG).show();
            }


        } else if (v.getId() == R.id.btnAttachment) {
            openGallery();
        }

    }


    public void openGallery() {
        Intent intent = new Intent();
        // Show only images, no videos or anything else
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // Always show the chooser (if there are multiple options available)
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            Picasso.with(getBaseContext()).load(uri).fit().centerInside().into(profilePic);
        }
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


}
