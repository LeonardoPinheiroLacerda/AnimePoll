package com.leonardo.animepoll.services;

import java.util.HashSet;
import java.util.Set;

import com.leonardo.animepoll.config.JikanConfig;
import com.leonardo.animepoll.models.Anime;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class JikanSeasonService {

    private final HttpClientService httpClientService;
    private final JikanConfig config;
    private final JsonMapperService jsonMapperService;
    
    public Set<Anime> findActual(){
        Set<Anime> season = new HashSet<>();
        Integer page = 1;

        while(true){
            JSONObject json = getPage(page);
            JSONArray data = json.getJSONArray("data");

            for(int i = 0; i < data.length(); i ++){
                JSONObject anime = new JSONObject(data.get(i).toString());
                Anime obj = jsonMapperService.jsonToAnime(anime);
                season.add(obj);
            }

            if(!json.getJSONObject("pagination").getBoolean("has_next_page"))
                break;

            page += 1;
        }

        return season;
    }

    private JSONObject getPage(Integer page){
        String uri = config.getBaseUrl() + "seasons/now?page=" + page;
        String response  = httpClientService.get(uri);
        return new JSONObject(response);       
    }

}
