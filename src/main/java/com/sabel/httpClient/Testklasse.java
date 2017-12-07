package com.sabel.httpClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Testklasse {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://bitaps.com/api/ticker/average");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.100.1", 8080));
        URLConnection urlConnection = url.openConnection(proxy);

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String line = null;
        String json = null;
        while ((line = br.readLine()) != null) {
            json = line;
            System.out.println(line);
        }
        String[] splits = json.split("eur");
        json = splits[1].substring(4, 11);
        System.out.println(json);
        br.close();

        System.out.println("---------------------------------------");


        URL urlNeu = new URL("https://bitaps.com/api/ticker/average");
        URLConnection urlConnectionNeu = urlNeu.openConnection(proxy);
        InputStream is = null;
        try{
            is = urlConnectionNeu.getInputStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            System.out.println(jsonObject.toString());
            JsonObject fx_rates = jsonObject.getJsonObject("fx_rates");
            System.out.println(fx_rates.toString());
            System.out.println(fx_rates.getString("eur"));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is != null){
                is.close();
            }
        }

    }
}
