package ru.itmentor.spring.rest.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Отключаем CSRF для REST API

//                .authorizeRequests(auth -> auth
//                        .antMatchers("/api/users/**", "/v3/api-docs/**").permitAll()
//                        .anyRequest().authenticated())

                .formLogin(form -> form.disable())  // Отключаем форму входа
                .httpBasic(basic -> basic.disable());  // Отключаем базовую аутентификацию

        return http.build();
    }
}