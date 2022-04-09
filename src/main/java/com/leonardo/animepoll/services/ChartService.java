package com.leonardo.animepoll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.animepoll.dtos.ChartItem;
import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.repositories.AnimeRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class ChartService {
    
    private final AnimeRepository repository;

    public List<ChartItem> getChart(){
        
        List<Anime> animes = repository.findAll()
            .stream().sorted((a1, a2) -> a2.getVotes().compareTo(a1.getVotes())).collect(Collectors.toList());;;

        List<ChartItem> chart = new ArrayList<>();

        for(int i = 0; i < animes.size(); i ++){

            Anime anime = animes.get(i);

            ChartItem item = new ChartItem();
            item.setRank(i + 1);
            item.setTitle(anime.getTitle());
            item.setMalScore(anime.getScore());
            item.setMalScoredBy(anime.getScoredBy());
            item.setVotes(anime.getVotes());
            item.setCover(anime.getCover());
            item.setJapaneseTitle(anime.getJapaneseTitle());

            chart.add(item);
        }

        return chart;
    }

}
