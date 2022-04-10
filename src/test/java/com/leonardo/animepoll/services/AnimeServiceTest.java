package com.leonardo.animepoll.services;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.verify;

import com.leonardo.animepoll.repositories.AnimeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnimeServiceTest {
    
    @Mock
    private JikanSeasonService jikanSeasonService;

    @Mock
    private AnimeRepository repository;


    @Mock 
    private JikanAnimeService jikanAnimeService;

    private AnimeService underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest = new AnimeService(jikanSeasonService, jikanAnimeService, repository);
    }

    @Test
    void shouldRunTheSaveMethodFromAnimeRepository(){

        underTest.saveSeason();

        verify(repository).saveAll(anySet());
        verify(jikanSeasonService).findActual();

    }


}
