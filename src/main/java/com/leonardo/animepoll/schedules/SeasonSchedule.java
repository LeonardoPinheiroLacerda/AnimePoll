package com.leonardo.animepoll.schedules;

import java.util.List;

import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.models.enums.Season;
import com.leonardo.animepoll.services.AnimeService;
import com.leonardo.animepoll.services.JikanSeasonService;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SeasonSchedule.class);

    /*
    * Second - Minute -  Hour -  Day of Month -  Month - Day of week
    */
    @Scheduled(cron = "0 * * * * *")
    public void checkAndUpdateSeason(){
        
        List<Anime> previousSeason = service.findAll(); 

        try{
            Season season = previousSeason.get(0).getSeason();
            Integer year = previousSeason.get(0).getYear();

            Boolean isSameSeason = jikanService
                .findActual()
                .stream()
                .anyMatch(anime -> anime.getSeason().equals(season) && anime.getYear().equals(year));

            if(!isSameSeason){
                service.deleteAll();
                service.saveSeason();

                LOGGER.info("A season foi renovada!");
            }
        }catch(IndexOutOfBoundsException e){
            service.saveSeason();
            LOGGER.info("A season foi criada no banco de dados!");
        }

        
    }

    /*
    * Second - Minute -  Hour -  Day of Month -  Month - Day of week
    */
    @Scheduled(cron = "0 0 0 * * *")
    public void updateData(){
        
        LOGGER.info("Os metadados estão sendo atualizados...");

        List<Anime> previousSeason = service.findAll(); 

        for (Anime anime : previousSeason) {
            try{
                Anime newData = service.findById(anime.getMalId());

                if(newData.getAiredTo() != null){
                    anime.setAiredTo(newData.getAiredTo());
                }

                anime.setCover(newData.getCover());
                anime.setTrailer(newData.getTrailer());
                anime.setScore(newData.getScore());
                anime.setScoredBy(newData.getScoredBy());
                anime.setStatus(newData.getStatus());
                anime.setMembers(newData.getMembers());

                System.out.println(newData.getMalId());
                
                service.save(anime);

                Thread.sleep(750L);

            } catch(InterruptedException | JSONException |NullPointerException e){
                LOGGER.warn("O resgistro de id " + anime.getId() + " não pode ser atualizado, provavelmente a aplicação fez mais requests que devia por minuto.");
            }

            LOGGER.info("Os metadados foram atualizados.");
        }

    }



}
