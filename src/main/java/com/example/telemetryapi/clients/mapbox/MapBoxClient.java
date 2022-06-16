package com.example.telemetryapi.clients.mapbox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MapBoxClient {

    @Value("${properties.mapbox.url}")
    private String url;

    @Value("${properties.mapbox.access_token}")
    private String accessToken;

    public String getGeoLocation(String longitude, String latitude) {
        String result = null;

        try {
            URL mapBoxUrl = getUrl(longitude, latitude);
            HttpURLConnection conn = (HttpURLConnection) mapBoxUrl.openConnection();
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

    private URL getUrl(String longitude, String latitude) throws MalformedURLException {
        String JSON = ".json?";
        String COMA = ",";
        return new URL(this.url + longitude + COMA + latitude + JSON + this.accessToken);
    }
}
