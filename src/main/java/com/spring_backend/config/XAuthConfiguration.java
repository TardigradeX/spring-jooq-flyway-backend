package com.spring_backend.config;

import com.spring_backend.security.xauth.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class XAuthConfiguration {

    @Bean
    public TokenProvider tokenProvider(XAuthProperties auth) {
        String secret = auth.getXAuth().getSecret();
        int validityInSeconds = auth.getXAuth().getTokenValidityInSeconds();
        return new TokenProvider(secret, validityInSeconds);
    }

}
