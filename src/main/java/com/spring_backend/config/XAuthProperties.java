package com.spring_backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by daniel on 5/12/17.
 */
@Component
@ConfigurationProperties(prefix = "authentication", ignoreUnknownFields = false)
public class XAuthProperties {

    private final XAuth xAuth = new XAuth();

    public XAuth getXAuth() { return xAuth; }

    public static class XAuth {
        private String secret;
        private int tokenValidityInSeconds;


        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public int getTokenValidityInSeconds() {
            return tokenValidityInSeconds;
        }

        public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
            this.tokenValidityInSeconds = tokenValidityInSeconds;
        }

    }

}
