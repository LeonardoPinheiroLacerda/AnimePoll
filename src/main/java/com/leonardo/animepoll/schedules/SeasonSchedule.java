package com.leonardo.animepoll.schedules;

import java.util.List;
import java.util.Set;

import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.models.enums.Season;
import com.leonardo.animepoll.services.AnimeService;
import com.leonardo.animepoll.services.JikanSeasonService;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Configuration
@EnableScheduling
public class SeasonSchedule {
    
    private final AnimeService service;
    private final JikanSeasonService jikanService;

    /*
    * Second - Minute -  Hour -  Day of Month -  Month - Day of week
    */
    @Scheduled(cron = "0 * * * * *")
    public void checkAndUpdateSeason(){
        
        List<Anime> previousSeason = service.findAll();

        Season season = previousSeason.get(0).getSeason();
        Integer year = previousSeason.get(0).getYear();

        Boolean isSameSeason = jikanService
            .findActual()
            .stream()
            .anyMatch(anime -> anime.getSeason().equals(season) && anime.getYear().equals(year));

        if(!isSameSeason){
            service.deleteAll();
            service.saveSeason();
        }

    }

}
