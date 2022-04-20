package com.leonardo.animepoll.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.leonardo.animepoll.exceptions.AlreadyVotedException;
import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.repositories.AnimeRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Service
public class AnimeService {
    
    private final JikanSeasonService jikanSeasonService;
    private final JikanAnimeService jikanAnimeService;
    private final AnimeRepository repository;

    public void saveSeason(){
        Set<Anime> season = jikanSeasonService.findActual();
        repository.saveAll(season);
    }

    public Anime save(Anime anime){
        return repository.save(anime);
    }

    public List<Anime> findAll(){
        return repository
            .findAll()
            .stream().sorted((a1, a2) -> a2.getMembers().compareTo(a1.getMembers())).collect(Collectors.toList());
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Anime vote(Long id, String cookie, HttpServletResponse response){
        if(cookie != null){
            throw new AlreadyVotedException("Apenas é possível votar uma vez a cada 24 horas.");
        }

        Anime anime = repository.findById(id).get();

        setAnimeCookie(anime.getMalId().toString(), response);

        anime.setVotes(anime.getVotes() + 1);
        repository.save(anime);

        return anime;
    
    }

    public Anime findById(Long id){
        return jikanAnimeService.findById(id);
    }

    private void setAnimeCookie(String value, HttpServletResponse response){
        ResponseCookie responseCookie = ResponseCookie.from("anime", value)
        .secure(true)
        .httpOnly(true)
        .path("/")
        .maxAge(60 * 60 * 24) //24 horas
        .build();

        response.setHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
    }
}
