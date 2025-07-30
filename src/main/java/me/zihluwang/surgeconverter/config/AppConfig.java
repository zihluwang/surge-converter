package me.zihluwang.surgeconverter.config;

import me.zihluwang.surgeconverter.property.AppProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties({AppProperty.class})
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .build();
    }
}
