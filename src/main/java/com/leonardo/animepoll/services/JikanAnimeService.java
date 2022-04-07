package com.leonardo.animepoll.services;

import com.leonardo.animepoll.config.JikanConfig;
import com.leonardo.animepoll.models.Anime;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JikanAnimeService {
    
    private final HttpClientService httpClientService;
    private final JikanConfig config;

    public Anime findById(Integer id){
        String uri = config.getBaseUrl() + "anime/" + id;

        System.out.println(uri);

        String response = httpClientService.get(uri);

        JSONObject json = new JSONObject(response);
        
        return jsonToAnime(json.getJSONObject("data"));
    }

    private Anime jsonToAnime(JSONObject json){
        return new Anime();
    }

}
