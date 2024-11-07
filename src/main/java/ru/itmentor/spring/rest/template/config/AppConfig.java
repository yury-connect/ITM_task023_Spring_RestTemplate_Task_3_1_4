package ru.itmentor.spring.rest.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.AuthToken;


@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name = {"token", "authToken"})
    public AuthToken authToken() {
        return new AuthToken();
    }
}
