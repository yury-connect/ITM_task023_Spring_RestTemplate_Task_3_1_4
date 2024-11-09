package ru.itmentor.spring.rest.template.service;

import org.springframework.http.ResponseEntity;
import ru.itmentor.spring.rest.template.model.User;


public interface UserServiceApi {

    // Создать нового пользователя
    ResponseEntity<String> createUser(User user);

    // Найти всех пользователей
    ResponseEntity<User[]> getAllUsers();

    // Обновить информацию о пользователе
    ResponseEntity<String> updateUser(Long id, User user);

    // Удалить пользователя по ID
    ResponseEntity<String> deleteUserById(Long id);

    // Выполнить весь скрипт согласно заданию
    ResponseEntity<String> executeScrypt();
}
