package com.leonardo.animepoll.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.springframework.stereotype.Service;

@Service
public class HttpClientService {
    
    public String get(String uri){
        
        try{
            HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                .GET()
                .timeout(Duration.ofSeconds(3))

                .build();

            HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        }catch(URISyntaxException e){
            return null;
        }catch(IOException | InterruptedException e){
            return null;
        }

    }

}
