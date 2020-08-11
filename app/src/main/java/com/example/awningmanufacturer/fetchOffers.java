package com.example.awningmanufacturer;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class fetchOffers extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "Offers Activity";
    String data = "";
    public JSONObject offer;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://awningmanufacturer.org/offer.php");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);
            JSONObject oData = (JSONObject) JO.get("data");
            JSONArray content = (JSONArray) oData.get("content");


            for(int i = 0; i < content.length(); i++){
                offer = content.getJSONObject(i);
                Iterator key = offer.keys();
                while (key.hasNext()) {
                    String k = key.next().toString();
                    System.out.println("Key : " + k + ", value : "
                            + offer.getString(k));
                }
                // System.out.println(objects.toString());
                System.out.println("-----------");
            }




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//
//        offers.data.setText(this.offer.toString());
    }
}
