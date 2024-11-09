package ru.itmentor.spring.rest.template.controller;

import org.springframework.http.ResponseEntity;
import ru.itmentor.spring.rest.template.model.User;


public interface RestTemplateUserController {

    // Создать нового пользователя
    ResponseEntity<String> createUser(User user);

    // Найти всех пользователей
    ResponseEntity<User[]> getAllUsers();

    // Обновить информацию о пользователе
    ResponseEntity<String> updateUser(Long id, User user);

    // Удалить пользователя по ID
    ResponseEntity<Void> deleteUserById(Long id);

    // Выполнить весь скрипт согласно заданию
    ResponseEntity<String> executeScrypt();
}
