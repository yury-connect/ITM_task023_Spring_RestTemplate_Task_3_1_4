package ru.itmentor.spring.rest.template.controller;

import org.springframework.http.ResponseEntity;
import ru.itmentor.spring.rest.template.model.User;

import java.util.List;


public interface RestTemplateUserController {

    // Создать нового пользователя
    ResponseEntity<String> createUser(User user);

    // Найти пользователя по ID
    ResponseEntity<User> getUserById(Long id);

    // Найти всех пользователей
    ResponseEntity<User[]> getAllUsers();

    // Обновить информацию о пользователе
    ResponseEntity<String> updateUser(Long id, User user);

    // Удалить пользователя по ID
    ResponseEntity<Void> deleteUserById(Long id);

    // Удалить всех пользователей
    ResponseEntity<Void> deleteAllUsers();

    // Выполнить весь скрипт согласно заданию
    ResponseEntity<String> executeScrypt();
}
