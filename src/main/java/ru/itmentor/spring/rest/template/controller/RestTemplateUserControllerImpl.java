package ru.itmentor.spring.rest.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.model.request.RequestDTO;
import ru.itmentor.spring.rest.template.model.response.Response;
import ru.itmentor.spring.rest.template.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itmentor.spring.rest.template.constants.Constants.SOURCE_URL;


/*
Контроллер предоставляет доступ к RESTful сервисам.
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestTemplateUserControllerImpl implements RestTemplateUserController {

    UserService userService;


    // 1. Создание одного пользователя (POST)
    @Override
    @Operation(summary = "Создание одного нового пользователя (POST)")
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED) // используется HttpStatus.CREATED (201)
                .body(userService.createUser(user));
    }

    // 2. Создание списка новых пользователей (POST)
    @Override
    @Operation(summary = "Создание списка новых пользователей (POST)")
    @PostMapping("/users/batch")
    public ResponseEntity<List<User>> createUsers(@RequestBody List<User> userList) {
        return ResponseEntity.status(HttpStatus.CREATED) // используется HttpStatus.CREATED (201)
                .body(userService.createUsers(userList));
    }

    // 3. Получение конкретного пользователя по ID (GET)
    @Override
    @Operation(summary = "Получение конкретного пользователя по ID (GET)")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    // 4. Получение всех пользователей (GET)
    @Override
    @Operation(summary = "Получение всех пользователей (GET)")
    @GetMapping("/users")
    public  ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    // 5. Обновление данных пользователя (PUT)
    @Override
    @Operation(summary = "Обновление данных пользователя (PUT)")
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") User user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    // 6. Удаление пользователя (DELETE)
    @Override
    @Operation(summary = "Удаление пользователя (DELETE)")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build(); // Возвращаем HttpStatus.NO_CONTENT (204)
    }

    // 7. Удаление всех пользователей (DELETE)
    @Override
    @Operation(summary = "Удаление всех пользователей (DELETE)")
    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build(); // Возвращаем HttpStatus.NO_CONTENT (204)
    }

//    private final RestTemplate restTemplate;

//    @Autowired
//    public RestTemplateUserController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }



    // step № 1
//    @GetMapping
//    public List<Response> getResponse() {
//        /*{
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Accept", "application/json");
//            HttpEntity entity = new HttpEntity(headers);
//
//            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
//                    .queryParam("param1", "value1")
//                    .queryParam("param2", "value2");
//
//            HttpEntity&lt;String&gt; response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
//        }*/
//        List<Response> list;
//        ResponseEntity<ArrayList> response = restTemplate.getForEntity(SOURCE_URL, ArrayList.class);
//        return response.getBody();
//    }





//    @PostMapping
//    public String getName(@RequestBody RequestDTO requestDTO) {
//        HttpEntity<RequestDTO> requestDTOHttpEntity = new HttpEntity<>(requestDTO);
//        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(SOURCE_URL, requestDTOHttpEntity, String.class);
//        return stringResponseEntity.getBody();
//    }

}
