package com.sabel.httpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

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
    }
}
