package io.realworld.common.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TokenProviderTest {

    @Autowired
    TokenProvider tokenProvider;
    
    @Test 
    void token() {
        //given

        //when

        //then
    }

    @Test
    void getDate() {
        //given
        LocalDateTime localDateTime = LocalDateTime.now().plus(86400, ChronoUnit.SECONDS);


        System.out.println(localDateTime);
        //when

        //then
    }
}