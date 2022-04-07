package com.leonardo.animepoll.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.leonardo.animepoll.config.JikanConfig;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



public class JikanSeasonServiceTest {

    @Mock
    private HttpClientService httpClientService;

    @Mock
    private JikanConfig config;

    @Mock
    private JsonMapperService jsonMapperService;

    private JikanSeasonService underTest;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        underTest = new JikanSeasonService(httpClientService, config, jsonMapperService);
    }

    @Test
    void shouldReturnTheActualSeason(){

        when(config.getBaseUrl()).thenReturn("https://api.jikan.moe/v4/");

        when(httpClientService.get(anyString())).thenCallRealMethod();

        when(jsonMapperService.jsonToAnime(any(JSONObject.class))).thenCallRealMethod();

        underTest.findActual();

    }



}
