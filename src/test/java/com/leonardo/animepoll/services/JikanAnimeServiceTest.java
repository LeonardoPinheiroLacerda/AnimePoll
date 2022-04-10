package com.leonardo.animepoll.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leonardo.animepoll.config.JikanConfig;
import com.leonardo.animepoll.models.Anime;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JikanAnimeServiceTest {

    @Mock
    private JikanConfig jikanconfig;

    @Mock
    private HttpClientService httpClientService;

    @Mock
    private JsonMapperService jsonMapperService;
    
    private JikanAnimeService underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        // jsonMapperService = new JsonMapperService();
        underTest = new JikanAnimeService(httpClientService, jikanconfig, jsonMapperService);
    }

    @Test
    void shouldReturnACowboyBebopAnimeObject(){

        //given
        Long id = 1L;

        //when
        when(jikanconfig.getBaseUrl()).thenReturn("https://api.jikan.moe/v4/");

        when(httpClientService.get(anyString()))
            .thenCallRealMethod();

        when(jsonMapperService.jsonToAnime(any(JSONObject.class)))
            .thenCallRealMethod();

        Anime actual = underTest.findById(id);

        //then
        Long expectedId = 1L;
        String expectedTitle = "Cowboy Bebop";

        assertEquals(expectedId, actual.getId());
        assertEquals(expectedTitle, actual.getTitle());

        verify(jsonMapperService).jsonToAnime(any(JSONObject.class));
        verify(httpClientService).get(anyString());

    }

}
