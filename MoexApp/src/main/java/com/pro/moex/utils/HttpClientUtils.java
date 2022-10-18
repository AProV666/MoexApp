package com.pro.moex.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientUtils {
    public String httpGet(String url) {
        String json = null;
        try {
            HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
