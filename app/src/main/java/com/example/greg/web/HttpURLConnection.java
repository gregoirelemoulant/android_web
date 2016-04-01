package com.example.greg.web;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class HttpURLConnection extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new DownloadTask().execute("http://10.5.207.205:8888/Accounts/pageconnexion");

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {

                return loadFromNetwork(urls[0]);
            } catch (IOException e) {
                return getString(R.string.connection_error);
            }


        }
    }
    /** Initiates the fetch operation. */
    private String loadFromNetwork(String urlString) throws IOException {
        InputStream stream = null;
        String str ="";

        try {
            stream = downloadUrl(urlString);

        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return str;
    }
    private InputStream downloadUrl(String urlString) throws IOException {


        // BEGIN_INCLUDE(get_inputstream)
        URL url = new URL(urlString);

        java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 /* milliseconds */);
        conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        // Start the query
        conn.connect();
        InputStream stream = conn.getInputStream();
        try {
            parseJsonFile(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream;
        // END_INCLUDE(get_inputstream)


}
    private void parseJsonFile (InputStream stream) throws Exception {

        JSONObject jsonObj = new JSONObject(stream.toString());

        JSONObject listeDevice =jsonObj.getJSONObject("listeDevice");
        JSONArray device = listeDevice.getJSONArray(("device"));
        for (int i=0;i<listeDevice.length();i++){
            String attributeId= device.getJSONObject(i).getString("id");
            String memberId= device.getJSONObject(i).getString("memberId");
            String description= device.getJSONObject(i).getString("description");
        }
    }
}


