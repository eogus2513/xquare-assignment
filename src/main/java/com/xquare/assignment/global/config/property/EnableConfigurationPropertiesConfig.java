package com.xquare.assignment.global.config.property;

import com.xquare.assignment.global.security.jwt.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {JwtProperties.class})
@Configuration
public class EnableConfigurationPropertiesConfig {
}
