package com.leonardo.animepoll.services;

import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.animepoll.dtos.ChartItemDTO;
import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.repositories.AnimeRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class ChartService {
    
    private final AnimeRepository repository;

    public List<ChartItemDTO> getChart(){
        
        List<Anime> animes = repository.findAll()
            .stream().sorted((a1, a2) -> a2.getVotes().compareTo(a1.getVotes())).collect(Collectors.toList());;;

        List<ChartItemDTO> chart = new ArrayList<>();

        for(int i = 0; i < animes.size(); i ++){

            Anime anime = animes.get(i);

            ChartItemDTO item = new ChartItemDTO();
            item.setRank(i + 1);
            item.setTitle(anime.getTitle());
            item.setMalScore(anime.getScore());
            item.setMalScoredBy(anime.getScoredBy());
            item.setVotes(anime.getVotes());
            item.setCover(anime.getCover());
            item.setJapaneseTitle(anime.getJapaneseTitle());
            item.setTrailer(anime.getTrailer());
            item.setUrl(anime.getUrl());
            


            if(anime.getAiredFrom() != null){
                item.setAiredFrom(anime.getAiredFrom().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                item.setDayOfWeek(this.dayOfWeekToString(anime.getAiredFrom().getDayOfWeek()));
            }
            if(anime.getAiredTo() != null) {
                item.setAiredTo(anime.getAiredTo().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));    
            }
            
            chart.add(item);
        }

        return chart;
    }

    private String dayOfWeekToString(DayOfWeek dow){

        switch(dow){

            case MONDAY: 
                return "Segunda-feira";
            case TUESDAY:
                return "Terça-feira";
            case WEDNESDAY:
                return "Quarta-feira";
            case THURSDAY:
                return "Quinta-feira";
            case FRIDAY:
                return "Sexta-feira";
            case SATURDAY:
                return "Sábado";
            case SUNDAY:
                return "Domingo";
            default:
                return "Não definido";
        }

    }

}
