package com.leonardo.animepoll.services;

import java.time.LocalDate;

import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.models.enums.Rating;
import com.leonardo.animepoll.models.enums.Season;
import com.leonardo.animepoll.models.enums.Status;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class JsonMapperService {
    
    public Anime jsonToAnime(JSONObject json){
        Anime anime = new Anime();

        anime.setId(json.getLong("mal_id"));
        anime.setTitle(json.getString("title"));
        anime.setJapaneseTitle(json.getString("title_japanese"));
        anime.setCover(json.getJSONObject("images").getJSONObject("jpg").getString("image_url"));
        anime.setYear(json.getInt("year"));
        anime.setUrl(json.getString("url"));
        anime.setMembers(json.getLong("members"));
        
        if(!json.isNull("score")){
            anime.setScore(json.getFloat("score"));
            anime.setScoredBy(json.getLong("scored_by"));
        }
        
        if(json.getJSONObject("trailer").isNull("url") == false){
            anime.setTrailer(json.getJSONObject("trailer").getString("url"));
        }

        anime.setVotes(0L);

        final JSONObject TO = json.getJSONObject("aired").getJSONObject("prop").getJSONObject("to");
        final JSONObject FROM = json.getJSONObject("aired").getJSONObject("prop").getJSONObject("from");
        
        if(TO.isNull("day") == false){
            anime.setAiredTo(LocalDate.of(TO.getInt("year"), TO.getInt("month"), TO.getInt("day")));
        }

        if(FROM.isNull("day") == false){
            anime.setAiredFrom(LocalDate.of(FROM.getInt("year"), FROM.getInt("month"), FROM.getInt("day")));
        }
        
        
        Rating[] ratingValues = Rating.values();
        for(int i = 0; i < ratingValues.length; i ++){
            String value = ratingValues[i].getRating();
            if(value.toLowerCase().equals(json.getString("rating").toLowerCase())){
                anime.setRating(ratingValues[i]);
            }
        }

        Status[] statusValues = Status.values();
        for(int i = 0; i < statusValues.length; i ++){
            String value = statusValues[i].getStatus();
            if(value.toLowerCase().equals(json.getString("status").toLowerCase())){
                anime.setStatus(statusValues[i]);
            }
        }

        Season[] seasonValues = Season.values();
        for(int i = 0; i < seasonValues.length; i ++){
            String value = seasonValues[i].getSeason();
            if(value.toLowerCase().equals(json.getString("season").toLowerCase())){
                anime.setSeason(seasonValues[i]);
            }
        }

        return anime;
    }


}
