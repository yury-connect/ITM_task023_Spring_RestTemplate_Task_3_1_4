package ru.itmentor.spring.rest.template.service;

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
 * Сервис для работы с пользователями через RESTful сервисы.
 * Реализует взаимодействие с внешними API с помощью RestTemplate.
 * @author Lapitski Yury
 */
@Service
public class UserServiceApiImpl implements UserServiceApi {

    private final RestTemplate restTemplate;
    private final AuthToken authToken;
    private final Logger logger;


    @Autowired
    public UserServiceApiImpl(RestTemplate restTemplate, AuthToken authToken, @Qualifier("serviceLogger") Logger logger) {
        this.restTemplate = restTemplate;
        this.authToken = authToken;
        this.logger = logger;
    }



    // **********   step № 2   **********
    @Override
    public ResponseEntity<String> createUser(User user) {
        logger.info("UserServiceApiImpl.createUser(User user) // \t user = '" + user + "';");
        return getResponse(
                URL_SOURCE,
                user, // Тело запроса — объект User, который нужно обновить
                HttpMethod.POST,
                String.class);
    }


    // **********   step № 1   **********
    @Override
    public ResponseEntity<User[]> getAllUsers() {
        logger.info("UserServiceApiImpl.getAllUsers();");
        ResponseEntity<User[]> response = getResponse(
                URL_SOURCE,
                null, // Нет тела запроса для GET-запроса
                HttpMethod.GET,
                User[].class);
        setSessionId(response.getHeaders());
        logger.info("UserServiceApiImpl.getAllUsers(): " + Arrays.toString(response.getBody()) + ";");
        return response;
    }


    // **********   step № 3   **********
    @Override
    public ResponseEntity<String> updateUser(Long id, User user) {
        logger.info("UserServiceApiImpl.updateUser(Long id, User user) // \t id = '" + id + "'; \t user = '" + user + "';");
        user.setId(id);
        return getResponse(
                URL_SOURCE,
                user, // Тело запроса — объект User, который нужно обновить
                HttpMethod.PUT,
                String.class);
    }


    // **********   step № 4   **********
    @Override
    public ResponseEntity<String> deleteUserById(Long id) {
        logger.info("UserServiceApiImpl.deleteUserById(Long id) // \t id = '" + id + "';");
        return getResponse( // Используем общий метод getResponse, чтобы вызвать удаление пользователя
                URL_SOURCE + "/" + id,  // Конечная точка удаления пользователя
                null,                    // Тело запроса не требуется для DELETE
                HttpMethod.DELETE,       // Метод запроса DELETE
                String.class);           // Ожидаемый тип ответа
    }


    // Выполнить весь скрипт согласно заданию
    @Override
    public ResponseEntity<String> executeScrypt() {
        User createdUser = User.builder()
                .id(3L)
                .name("James")
                .lastName("Brown")
                .age((byte) 31)
                .build();

        User updatedUser = User.builder()
                .id(3L)
                .name("Thomas")
                .lastName("Shelby")
                .age((byte) 18)
                .build();

        StringBuilder result = new StringBuilder();

        // Получаем всех пользователей и устанавливаем JSESSIONID
        this.getAllUsers(); // Просто вызываем метод, чтобы установить сессионный идентификатор

        // Создаем пользователя и добавляем часть результата в итоговый код
        ResponseEntity<String> createResponse = this.createUser(createdUser);
        if (createResponse.getStatusCode() == HttpStatus.OK) {
            result.append(createResponse.getBody()); // part № 1
        }

        // Обновляем пользователя и добавляем часть результата в итоговый код
        ResponseEntity<String> updateResponse = this.updateUser(3L, updatedUser);
        if (updateResponse.getStatusCode() == HttpStatus.OK) {
            result.append(updateResponse.getBody()); // part № 2
        }

        // Удаляем пользователя и добавляем часть результата в итоговый код
        ResponseEntity<String> deleteResponse = this.deleteUserById(3L);
        if (deleteResponse.getStatusCode() == HttpStatus.OK) {
            result.append(deleteResponse.getBody()); // part № 3
        }

        logger.info("UserServiceApiImpl: Result code = '" + result + "';");
        return ResponseEntity.ok(result.toString());
    }


    // ******************************   Сервисные методы   ******************************
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
        logger.info("UserServiceApiImpl: *** new value 'JSESSIONID'  = '" + authToken.getJSessionId() + "' ***;");
    }


    private <T, U> ResponseEntity<T> getResponse(String url,
                                                 U requestBody,
                                                 HttpMethod httpMethod,
                                                 Class<T> responseType) {

        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Если тело запроса не нужно (например, для DELETE), используем конструктор без тела
        HttpEntity<U> requestEntity = (requestBody != null)
                ? new HttpEntity<>(requestBody, headers)
                : new HttpEntity<>(headers);

        // Выполнение запроса с использованием restTemplate
        ResponseEntity<T> response = restTemplate.exchange(
                url,           // URL для запроса
                httpMethod,    // Метод (GET, POST, PUT, DELETE)
                requestEntity, // Тело запроса и заголовки
                responseType   // Тип ответа
        );

        // Логирую результат
        if (response.getStatusCode() == HttpStatus.OK) {
            logger.info("UserServiceApiImpl.getResponse(...): Результат операции - УСПЕХ :) // Статус: "
                    + response.getStatusCode() + ";");
        } else {
            logger.warning("UserServiceApiImpl.getResponse(...): Результат операции - ОШИБКА :( // Статус: "
                    + response.getStatusCode() + ";");
        }
        return response;
    }
}
