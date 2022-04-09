package com.leonardo.animepoll.resources;

import com.leonardo.animepoll.models.Anime;
import com.leonardo.animepoll.services.AnimeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/animes")
public class AnimeResource {
    
    private final AnimeService service;

    @PostMapping("/vote/{id}")
    public ResponseEntity<Anime> vote(@PathVariable Long id){
        Anime anime = service.vote(id);
        return ResponseEntity.ok(anime);
    }
}
