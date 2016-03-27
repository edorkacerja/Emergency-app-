package com.example.acerpc.emergencyapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmergencyApp extends AppCompatActivity   {

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
                        Toast.makeText(EmergencyApp.this, "Emergency message sent", Toast.LENGTH_SHORT).show();
                        sendMail("edorkacerja@gmail.com","danger", "someone is in danger");

//                        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
//                        sendIntent.putExtra("sms_body", "default content");
//                        sendIntent.setType("vnd.android-dir/mms-sms");
//                        startActivity(sendIntent);

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







    private MimeMessage createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("edorkacerja@gmail.com", "Tiemen Schut"));
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }





    private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            MimeMessage message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }





    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("edorkacerja@gmail.com","Ildailda1");
            }
        });
    }




    private class SendMailTask extends AsyncTask<MimeMessage, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(EmergencyApp.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(MimeMessage... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }










}
