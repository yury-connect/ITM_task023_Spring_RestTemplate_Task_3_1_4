package ru.itmentor.spring.rest.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.controller.RestTemplateUserControllerImpl;
import ru.itmentor.spring.rest.template.model.AuthToken;
import ru.itmentor.spring.rest.template.service.UserServiceApiImpl;

import java.util.logging.Logger;


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

    @Bean(name = "serviceLogger")
    public Logger userServiceImplLogger() {
        return Logger.getLogger(UserServiceApiImpl.class.getSimpleName());
    }

    @Bean(name = "controllerLogger")
    public Logger restTemplateUserControllerImplLogger() {
        return Logger.getLogger(RestTemplateUserControllerImpl.class.getSimpleName());
    }
}
