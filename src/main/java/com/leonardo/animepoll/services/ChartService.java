package com.leonardo.animepoll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.leonardo.animepoll.dtos.ChartItemDTO;
import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.repositories.AnimeRepository;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
                    
            chart.add(item);
        }

        return chart;
    }

    public String getChartHtml(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setCacheable(false);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        templateResolver.setForceTemplateMode(true);

        templateEngine.setTemplateResolver(templateResolver);

        Context ctx = new Context();
    
        ctx.setVariable("chart", getChart().subList(0, 10));
        final String result = templateEngine.process("fragments/chartItems", ctx);
        return result;
    }
    
}
