package ru.itmentor.spring.rest.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.service.UserServiceApi;

import java.util.logging.Logger;


/**
 * Контроллер для работы с пользователями через RESTful сервисы.
 * Реализует взаимодействие с внешними API с помощью RestTemplate.
 * @author Lapitski Yury
 */
@RestController
@RequestMapping("/api/users")
public class RestTemplateUserControllerImpl implements RestTemplateUserController {

    private final UserServiceApi userServiceApi;
    private final Logger logger;


    public RestTemplateUserControllerImpl(UserServiceApi userService, @Qualifier("controllerLogger") Logger logger) {
        this.userServiceApi = userService;
        this.logger = logger;
    }



    // **********   step № 2   **********
    // Создание нового пользователя
    @Override
    @Operation(summary = "Создание одного нового пользователя (POST)")
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody User user) {
        logger.info("RestTemplateUserControllerImpl.createUser(User user) // \t user = '" + user + "';");
        return userServiceApi.createUser(user);
    }


    // **********   step № 1   **********
    // Получение списка всех пользователей
    @Override
    @Operation(summary = "Получение всех пользователей (GET)")
    @GetMapping()
    public  ResponseEntity<User[]> getAllUsers() {
        logger.info("RestTemplateUserControllerImpl.createUser(User user);");
        return userServiceApi.getAllUsers();
    }


    // **********   step № 3   **********
    // Обновление данных существующего пользователя
    @Override
    @Operation(summary = "Обновление данных существующего пользователя (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("RestTemplateUserControllerImpl.updateUser(Long id,  User user) // \t id = '"
                + id + "'; \t user = '" + user + "';");
        return userServiceApi.updateUser(id, user);
    }


    // **********   step № 4   **********
    // Удаление пользователя по ID
    @Override
    @Operation(summary = "Удаление пользователя по ID (DELETE)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        logger.info("RestTemplateUserControllerImpl.deleteUserById(Long id) // \t id = '" + id + "';");
        userServiceApi.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


    // **********   Выполнение полного скрипта для выполнения задания   **********
    @Override
    @Operation(summary = "Выполнить полный скрипт и получить код согласно задания (GET)")
    @GetMapping("/execute_scrypt")
    public ResponseEntity<String> executeScrypt() {
        logger.info("RestTemplateUserControllerImpl.executeScrypt();");
        return userServiceApi.executeScrypt();
    }
}
