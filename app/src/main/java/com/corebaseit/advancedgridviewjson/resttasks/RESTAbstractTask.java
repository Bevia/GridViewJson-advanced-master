package com.corebaseit.advancedgridviewjson.resttasks;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vincentbevia on 06/10/16.
 */

public abstract class RESTAbstractTask extends AsyncTask<String, Integer, String> {

    protected String result;
    protected Object callable;
    private URL url;
    private int status;

    protected void setUrl(URL url) {
        this.url = url;
    }

    public int getHTTPStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public void setCallable(Object callable) {
        this.callable = callable;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            HttpURLConnection connection = (HttpURLConnection)
                    this.url.openConnection();

            connection.setInstanceFollowRedirects(false);
            connection.setUseCaches(false);

            this.status = connection.getResponseCode();

            String inputLine;
            BufferedReader reader;

            reader = new BufferedReader(
                          new InputStreamReader(
                            connection.getInputStream()));

            connection.getErrorStream();

            String message = connection.getResponseMessage();
            Log.d("HTTPMessages", "messages: " + message);

            StringBuffer response = new StringBuffer();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();
            connection.disconnect();

            return response.toString();

        } catch (IOException ioe) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        this.result = str;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}