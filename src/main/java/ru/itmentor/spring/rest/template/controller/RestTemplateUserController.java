package ru.itmentor.spring.rest.template.controller;

import org.springframework.http.ResponseEntity;
import ru.itmentor.spring.rest.template.model.User;

import java.util.List;


public interface RestTemplateUserController {

    // Создать нового пользователя
    ResponseEntity<User> createUser(User user);

    // Создать список новых пользователей
    ResponseEntity<List<User>> createUsers(List<User> userList);

    // Найти пользователя по ID
    ResponseEntity<User> findUserById(Integer id);

    // Найти всех пользователей
    ResponseEntity<List<User>> findAllUsers();

    // Обновить информацию о пользователе
    ResponseEntity<User> updateUser(User user);

    // Удалить пользователя по ID
    ResponseEntity<Void> deleteUserById(Integer id);

    // Удалить всех пользователей
    ResponseEntity<Void> deleteAllUsers();
}
