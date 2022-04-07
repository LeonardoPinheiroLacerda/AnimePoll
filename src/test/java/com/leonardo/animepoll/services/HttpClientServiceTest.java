package com.leonardo.animepoll.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HttpClientServiceTest {

    private HttpClientService underTest;

    @BeforeEach
    void init(){
        underTest = new HttpClientService();
    }
    
    @Test
    void shouldReturnTheResponseBody() {

        //given
        String uri = "http://www.google.com";

        //when
        String actual = underTest.get(uri);

        //then
        assertNotNull(actual);
    }

    @Test
    void shouldReturnNullWhenRecieveInvalidURI(){
        //given
        String uri = "http://www.google.com.ee";

        //when
        String actual = underTest.get(uri);

        //then
        assertNull(actual);
    }
}
