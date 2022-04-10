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
    private final JsonMapperService jsonMapperService;

    public Anime findById(Long id){
        String uri = config.getBaseUrl() + "anime/" + id;

        String response = httpClientService.get(uri);

        JSONObject json = new JSONObject(response);
        
        return jsonMapperService.jsonToAnime(json.getJSONObject("data"));
    }

   
}
