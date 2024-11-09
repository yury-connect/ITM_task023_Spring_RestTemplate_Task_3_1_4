package ru.itmentor.spring.rest.template.service;
import org.springframework.http.ResponseEntity;
import ru.itmentor.spring.rest.template.model.User;

import java.util.List;


public interface UserService {

    // Создать нового пользователя
    ResponseEntity<String> createUser(User user);

    // Найти пользователя по ID
    ResponseEntity<User> getUserById(Long id);

    // Найти всех пользователей
    ResponseEntity<User[]> getAllUsers();

    // Обновить информацию о пользователе
    ResponseEntity<String> updateUser(Long id, User user);

    // Удалить пользователя по ID
    ResponseEntity<String> deleteUserById(Long id);

    // Удалить всех пользователей
    ResponseEntity<String> deleteAllUsers();

    ResponseEntity<String> executeScrypt(User user);
}
