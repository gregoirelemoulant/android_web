package com.example.greg.web;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.net.URI;
import java.util.Locale;


public class MainActivity extends AppCompatActivity{

    private static int i;
    private Button b ;
    private ImageButton bMap, bStat,bConnection;
    Configuration config;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        config=new Configuration(getResources().getConfiguration());










        bMap =(ImageButton) findViewById(R.id.mapButton);

        bMap.setOnClickListener( new View.OnClickListener() {

                @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                        startActivity(intent);
                    }
                });




        bStat =(ImageButton) findViewById(R.id.statButton);

        bStat.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HttpURLConnection.class);
                startActivity(intent);
            }
        });


        bConnection =(ImageButton) findViewById(R.id.exchangeButton);

        bConnection.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {


            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_LONG).show();

                return true;

            case R.id.action_english:
                Toast.makeText(this, "language english selected", Toast.LENGTH_LONG).show();

                config=new Configuration(getResources().getConfiguration());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    config.setLocale(Locale.ENGLISH);
                }
                return true;


            case R.id.action_francais:
                Toast.makeText(this, "language français selected", Toast.LENGTH_LONG).show();
                config=new Configuration(getResources().getConfiguration());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    config.setLocale(Locale.FRENCH);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }




}



