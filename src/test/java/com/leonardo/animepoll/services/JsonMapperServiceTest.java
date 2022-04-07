package com.leonardo.animepoll.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JsonMapperServiceTest {

    private JsonMapperService underTest;

    @BeforeEach
    void setup(){
        underTest = new JsonMapperService();
    }

    @Test
    void shouldThrowsAnException() {

        //given
        JSONObject json = new JSONObject();

        //when
        //then
        assertThrows(JSONException.class, () -> underTest.jsonToAnime(json));

    }
}
