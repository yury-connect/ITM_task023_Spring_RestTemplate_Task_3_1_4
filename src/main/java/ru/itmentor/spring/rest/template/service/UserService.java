package ru.itmentor.spring.rest.template.service;
import ru.itmentor.spring.rest.template.model.User;

import java.util.List;


public interface UserService {

    // Создать нового пользователя
    User createUser(User user);

    // Создать список новых пользователей
    List<User> createUsers(List<User> userList);

    // Найти пользователя по ID
    User findUserById(int id);

    // Найти пользователя по userName
    User findUserByUsername(String userName);

    // Найти всех пользователей
    List<User> findAllUsers();

    // Обновить информацию о пользователе
    User updateUser(User user);

    // Удалить пользователя по ID
    User deleteUserById(int id);

    // Удалить всех пользователей
    User deleteAllUsers();
}
