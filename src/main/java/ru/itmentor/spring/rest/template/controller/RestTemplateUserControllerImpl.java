package ru.itmentor.spring.rest.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.service.UserService;

import java.util.logging.Logger;


/*
Контроллер предоставляет доступ к RESTful сервисам.
 */
@RestController
@RequestMapping("/api/users")
public class RestTemplateUserControllerImpl implements RestTemplateUserController {

    private final UserService userService;
    private final Logger logger;


    public RestTemplateUserControllerImpl(UserService userService, @Qualifier("controllerLogger") Logger logger) {
        this.userService = userService;
        this.logger = logger;
    }



    // **********   step № 2   **********
    @Override
    @Operation(summary = "Создание одного нового пользователя (POST)")
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody User user) {
        logger.info("RestTemplateUserControllerImpl: createUser( " + user + " )");
        return userService.createUser(user);
    }


    // **********   step № 1   **********
    @Override
    @Operation(summary = "Получение всех пользователей (GET)")
    @GetMapping()
    public  ResponseEntity<User[]> getAllUsers() {
        logger.info("RestTemplateUserControllerImpl: getAllUsers()");
        return userService.getAllUsers();
    }


    // **********   step № 3   **********
    @Override
    @Operation(summary = "Обновление данных пользователя (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("RestTemplateUserControllerImpl: updateUser( " + id + " ; " + user + " )");
        return userService.updateUser(id, user);
    }


    // **********   step № 4   **********
    @Override
    @Operation(summary = "Удаление пользователя (DELETE)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        logger.info("RestTemplateUserControllerImpl: deleteUserById( " + id + " )");
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build(); // Возвращаем HttpStatus.NO_CONTENT (204)
    }


    // **********   Выполнить полный скрипт и получить код согласно задания (GET)   **********
    @Override
    @Operation(summary = "Выполнить полный скрипт и получить код согласно задания (GET)")
    @GetMapping("/execute_scrypt")
    public ResponseEntity<String> executeScrypt() {
        logger.info("RestTemplateUserControllerImpl: executeScrypt()");
        return userService.executeScrypt();
    }
}
