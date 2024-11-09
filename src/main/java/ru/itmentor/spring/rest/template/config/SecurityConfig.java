package ru.itmentor.spring.rest.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Отключаем CSRF (новый синтаксис для более новых версий)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Разрешаем доступ ко всем URL без аутентификации
                )
                .formLogin(form -> form.disable())  // Отключаем форму входа
                .httpBasic(basic -> basic.disable());  // Отключаем базовую аутентификацию

        return http.build();
    }
}