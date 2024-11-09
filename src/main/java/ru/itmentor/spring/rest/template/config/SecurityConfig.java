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

                .authorizeRequests(auth -> auth
                        .antMatchers("/api/users/**").permitAll()
                        .anyRequest().authenticated())

//                .csrf(csrf -> csrf.disable())  // Отключаем CSRF (новый синтаксис для более новых версий)
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()  // Разрешаем доступ ко всем URL без аутентификации
//                )

                .formLogin(form -> form.disable())  // Отключаем форму входа
                .httpBasic(basic -> basic.disable());  // Отключаем базовую аутентификацию

        return http.build();
    }
}