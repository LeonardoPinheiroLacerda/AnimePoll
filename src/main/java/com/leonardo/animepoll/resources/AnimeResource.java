package com.leonardo.animepoll.resources;

import com.leonardo.animepoll.services.AnimeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@RestController
@RequestMapping("/animes")
public class AnimeResource {
    
    private final AnimeService service;

    @GetMapping("/vote/{id}")
    public ResponseEntity<Void> vote(@PathVariable Long id){
        service.vote(id);
        return ResponseEntity.ok().build();
    }
}
