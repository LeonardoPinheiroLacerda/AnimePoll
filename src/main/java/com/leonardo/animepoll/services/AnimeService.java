package com.leonardo.animepoll.services;

import java.util.List;
import java.util.Set;

import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.repositories.AnimeRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AnimeService {
    
    private final JikanSeasonService jikanSeasonService;
    private final AnimeRepository repository;

    public void saveSeason(){
        Set<Anime> season = jikanSeasonService.findActual();
        repository.saveAll(season);
    }

    public List<Anime> findAll(){
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
