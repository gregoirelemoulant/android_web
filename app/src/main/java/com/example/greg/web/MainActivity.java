package com.example.greg.web;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
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


public class MainActivity extends AppCompatActivity implements firstFragment.OnFragmentInteractionListener {

    private static int i;
    private Button b ;
    private ImageButton bMap, bStat;
    private firstFragment first;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        first = firstFragment.newInstance("first fragment", "you");

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            getFragmentManager().beginTransaction().add(R.id.myFrame, first).commit();


        b = (Button) findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                firstFragment second = firstFragment.newInstance("second fragment " + i, "you");
                android.app.FragmentTransaction fgt = getFragmentManager().beginTransaction();
                fgt.addToBackStack("new fragment");


                // important code for orientation based adaptation

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Toast.makeText(getApplicationContext(), "myFrame Replacement", Toast.LENGTH_SHORT).show();

                    fgt.replace(R.id.myFrame, second).commit();
                } else {
                    fgt.replace(R.id.myFrame2, second).commit();
                    Toast.makeText(getApplicationContext(), "myFrame2 Replacement", Toast.LENGTH_SHORT).show();
                    System.out.println("myFrame2 replacement");
                }

                //Toast.makeText(getBaseContext(), "Button Click", Toast.LENGTH_SHORT).show();
            }
        });


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

            case R.id.action_third:
                Toast.makeText(this, "Third selected", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Object obj) {
        System.out.println("Fragment Test");
    }
}

