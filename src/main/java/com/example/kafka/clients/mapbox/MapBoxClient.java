package com.example.kafka.clients.mapbox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class MapBoxClient {

    public String getGeoLocation(String longitude, String latitude) {
        String result = null;

        try {

            URL url = new URL("https://api.mapbox.com/geocoding/v5/mapbox.places/" + longitude + "," + latitude + ".json?access_token=pk.eyJ1IjoiZmVybmFuZG84NjEyIiwiYSI6ImNsNDdleHU5eDBkc2YzZG14Z2xzNGg2dzkifQ.-rg_8zchYXQ0VMBscMhf3A&types=address");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                result = output;
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }

        return result;
    }
}
