package com.xquare.assignment.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private final String secret;
    private final JwtExp exp;

    @Getter
    @AllArgsConstructor
    public static class JwtExp {
        private final Long access;
        private final Long refresh;
    }
}
