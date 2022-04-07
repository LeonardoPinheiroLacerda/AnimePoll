package com.leonardo.animepoll.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.leonardo.animepoll.config.JikanConfig;
import com.leonardo.animepoll.models.Anime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JikanAnimeServiceTest {

    @Mock
    private JikanConfig jikanconfig;
    @Mock
    private HttpClientService httpClientService;
    
    private JikanAnimeService underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest = new JikanAnimeService(httpClientService,jikanconfig);
    }

    @Test
    void shouldPass(){

        //given
        Integer id = 1;

        //when
        when(jikanconfig.getBaseUrl()).thenReturn("https://api.jikan.moe/v4/");
        when(httpClientService.get("https://api.jikan.moe/v4/anime/1")).thenCallRealMethod();

        Anime actual = underTest.findById(id);

        //then
        Long expectedId = 1L;
        String expectedTitle = "Cowboy Bebop";

        assertEquals(expectedId, actual.getId());
        assertEquals(expectedTitle, actual.getTitle());

    }

}
