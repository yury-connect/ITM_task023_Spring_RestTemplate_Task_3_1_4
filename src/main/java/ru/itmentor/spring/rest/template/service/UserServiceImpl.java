package ru.itmentor.spring.rest.template.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.*;

import java.util.Arrays;
import java.util.logging.Logger;

import static ru.itmentor.spring.rest.template.constants.Constants.URL_SOURCE;


/**
 *      Этот слой вызывает RestTemplate, обрабатывает данные и взаимодействует с внешними API, если нужно.
 **/
@Service
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final AuthToken authToken;
    private final Logger logger;


    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, AuthToken authToken, @Qualifier("serviceLogger") Logger logger) {
        this.restTemplate = restTemplate;
        this.authToken = authToken;
        this.logger = logger;
    }


    // **********   step № 2   **********
    @Override
    public ResponseEntity<String> createUser(User user) {

        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Создаем HttpEntity, в которую добавляем объект User и заголовки
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        // Используем RestTemplate для выполнения PUT-запроса
        ResponseEntity<String> response = restTemplate.exchange(
                URL_SOURCE,
                HttpMethod.POST,
                request,
                String.class, new Object[0]);

        // Проверка статуса ответа
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Пользователь успешно добавлен/ создан. Статус: " + response.getStatusCode());
        } else {
            logger.info("Ошибка при добавлении/ создании пользователя: " + response.getStatusCode());
        }
        return response;
    }

    // **********   step № 1   **********
    @Override
    public ResponseEntity<User[]> getAllUsers() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(null, headers);
//        ResponseEntity<User[]> response = restTemplate.exchange(
//                URL_SOURCE,
//                HttpMethod.GET,
//                request,
//                User[].class);

        ResponseEntity<User[]> response = getResponse(
                String.class, // запрос без тела запроса
                HttpMethod.GET,
                User[].class);

        setSessionId(response.getHeaders());
        Arrays.stream(response.getBody()).forEach(user -> System.out.println(user));
        return response;
    }


    // **********   step № 3   **********
    @Override
    public ResponseEntity<String> updateUser(Long id, User user) {
        user.setId(id);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId());
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<User> request = new HttpEntity<>(user, headers);
//        ResponseEntity<String> response = restTemplate.exchange(URL_SOURCE,
//                HttpMethod.PUT,
//                request,
//                String.class);

        ResponseEntity<User[]> response = getResponse(
                String.class, // запрос без тела запроса
                HttpMethod.GET,
                User[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Пользователь успешно обновлен. Статус: " + response.getStatusCode());
        } else {
            logger.info("Ошибка при обновлении пользователя: " + response.getStatusCode());
        }
        return response;
    }


    // **********   step № 4   **********
    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId());
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                URL_SOURCE + "/" + id,
                HttpMethod.DELETE,
                requestEntity,
                String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("Пользователь успешно удален. Статус: " + response.getStatusCode());
        } else {
            logger.info("Ошибка при удалении пользователя: " + response.getStatusCode());
        }
        return response;
    }







    // Выполнить весь скрипт согласно заданию
    @Override
    public ResponseEntity<String> executeScrypt() {
        User createdUser = User.builder()
                .id(3L)
                .name("James")
                .lastName("Brown")
                .age((byte)31).build();
        User updatedUser = User.builder()
                .id(3L)
                .name("Thomas")
                .lastName("Shelby")
                .age((byte)18)
                .build();
        StringBuilder result = new StringBuilder();

        this.getAllUsers().getBody(); // we don't add it, we just call it
        result.append(this.createUser(createdUser).getBody()); // part № 1
        result.append(this.updateUser(3L, updatedUser).getBody()); // part № 2
        result.append(this.deleteUserById(3L).getBody()); // part № 3

        logger.info("Result code = '" + result + "'");
        return ResponseEntity.ok(result.toString());
    }


    // **********************************************************************
    private void setSessionId(HttpHeaders headers) {
        String jSessionIdReceived = headers.get("Set-Cookie")
                .stream()
                .filter(cookie -> cookie.startsWith("JSESSIONID"))
                .findFirst()
                .orElse("null")
                .replace("JSESSIONID=", "")
                .split(";")
                [0];
            authToken.setJSessionId(jSessionIdReceived.trim());

        System.out.println("\n\t\t*** new value 'JSESSIONID'  = " + authToken.getJSessionId());
    }





    private <T, S> ResponseEntity<T> getResponse(S requestBody,
                                                 HttpMethod httpMethod,
                                                 Class<T> responseType) {
        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Создаем HttpEntity, в которую добавляем объект requestBody и заголовки
        HttpEntity<S> requestEntity = new HttpEntity<>(requestBody, headers);

        // Выполнение запроса с использованием restTemplate
        return restTemplate.exchange(
                URL_SOURCE,
                httpMethod,
                requestEntity,
                responseType);
    }
}



