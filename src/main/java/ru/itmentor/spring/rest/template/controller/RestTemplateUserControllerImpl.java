package ru.itmentor.spring.rest.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.service.UserService;

import java.util.List;


/*
Контроллер предоставляет доступ к RESTful сервисам.
 */
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class RestTemplateUserControllerImpl implements RestTemplateUserController {

    UserService userService;


    // **********   step № 2   **********
    @Override
    @Operation(summary = "Создание одного нового пользователя (POST)")
    @PostMapping()
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("\n\n\tRestTemplateUserControllerImpl: createUser( " + user + " )\n\n");
        return userService.createUser(user);
    }


    @Override
    @Operation(summary = "Получение конкретного пользователя по ID (GET)")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        System.out.println("\n\n\tRestTemplateUserControllerImpl: getUserById( " + id + " )\n\n");
        return userService.getUserById(id);
    }


    // **********   step № 1   **********
    @Override
    @Operation(summary = "Получение всех пользователей (GET)")
    @GetMapping()
    public  ResponseEntity<User[]> getAllUsers() {
        System.out.println("\n\n\tRestTemplateUserControllerImpl: findAllUsers()\n\n");
        return userService.getAllUsers();
    }


    @Override
    @Operation(summary = "Обновление данных пользователя (PUT)")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        System.out.println("\n\n\tRestTemplateUserControllerImpl: updateUser( " + id + " ; " + user + " )\n\n");
        return userService.updateUser(id, user);
    }


    @Override
    @Operation(summary = "Удаление пользователя (DELETE)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build(); // Возвращаем HttpStatus.NO_CONTENT (204)
    }

    @Override
    @Operation(summary = "Удаление всех пользователей (DELETE)")
    @DeleteMapping()
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build(); // Возвращаем HttpStatus.NO_CONTENT (204)
    }

    // 7. Удаление всех пользователей (DELETE)
    @Override
    @Operation(summary = "Выполнить полный скрипт и получить код согласно задания (GET)")
    @GetMapping("/execute_scrypt")
    public ResponseEntity<String> executeScrypt() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}
