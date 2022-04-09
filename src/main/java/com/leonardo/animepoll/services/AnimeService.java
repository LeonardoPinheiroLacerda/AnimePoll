package com.leonardo.animepoll.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return repository
            .findAll()
            .stream().sorted((a1, a2) -> a2.getMembers().compareTo(a1.getMembers())).collect(Collectors.toList());
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
