package ru.itmentor.spring.rest.template.service;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.*;

import java.util.Arrays;

import static ru.itmentor.spring.rest.template.constants.Constants.URL_SOURCE;


/*
Этот слой вызывает RestTemplate, обрабатывает данные и взаимодействует с внешними API, если нужно.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;
    private final AuthToken authToken;



    // **********   step № 2   **********
    @Override
    public ResponseEntity<String> createUser(User user) {

        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Создаем HttpEntity, в которую добавляем объект User и заголовки
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Используем RestTemplate для выполнения PUT-запроса
        ResponseEntity<String> response = restTemplate.exchange(URL_SOURCE, HttpMethod.POST, requestEntity, String.class, new Object[0]);

//        refreshSessionId(response.getHeaders());

        // Проверка статуса ответа
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Пользователь успешно добавлен/ создан. Статус: " + response.getStatusCode());
        } else {
            System.out.println("Ошибка при добавлении/ создании пользователя: " + response.getStatusCode());
        }
        return response;
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {

        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Создаем HttpEntity, в которую добавляем объект User и заголовки
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);

        // Используем RestTemplate для выполнения PUT-запроса
        ResponseEntity<User> response = restTemplate.exchange(URL_SOURCE + "/" + id, HttpMethod.GET, requestEntity, User.class);

        // Проверка статуса ответа
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Пользователь успешно обновлен. Статус: " + response.getStatusCode());
        } else {
            System.out.println("Ошибка при обновлении пользователя: " + response.getStatusCode());
        }
        return response;
    }

    // **********   step № 1   **********
    @Override
    public ResponseEntity<User[]> getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<User[]> response = restTemplate.exchange(URL_SOURCE, HttpMethod.GET, request, User[].class);
        setSessionId(response.getHeaders());

        Arrays.stream(response.getBody()).forEach(user -> System.out.println(user));
        return response;
    }

    // **********   step № 3   **********
    @Override
    public ResponseEntity<String> updateUser(Long id, User user) {
        // Обновляем объект User новыми данными (id)
        user.setId(id);

        // Создаем HttpHeaders и добавляем заголовок Authorization с токеном
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId()); // Используем сессионный идентификатор из куки
        headers.setContentType(MediaType.APPLICATION_JSON); // Устанавливаем тип контента JSON

        // Создаем HttpEntity, в которую добавляем объект User и заголовки
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);

        // Используем RestTemplate для выполнения PUT-запроса
        ResponseEntity<String> response = restTemplate.exchange(URL_SOURCE, HttpMethod.PUT, requestEntity, String.class);

//        refreshSessionId(response.getHeaders());

        // Проверка статуса ответа
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Пользователь успешно обновлен. Статус: " + response.getStatusCode());
        } else {
            System.out.println("Ошибка при обновлении пользователя: " + response.getStatusCode());
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
        ResponseEntity<String> response = restTemplate.exchange(URL_SOURCE + "/" + id, HttpMethod.DELETE, requestEntity, String.class);

//        refreshSessionId(response.getHeaders());

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Пользователь успешно удален. Статус: " + response.getStatusCode());
        } else {
            System.out.println("Ошибка при удалении пользователя: " + response.getStatusCode());
        }
        return response;
    }

    @Override
    public ResponseEntity<String> deleteAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + authToken.getJSessionId());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(URL_SOURCE, HttpMethod.DELETE, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Пользователь успешно удален. Статус: " + response.getStatusCode());
        } else {
            System.out.println("Ошибка при удалении пользователя: " + response.getStatusCode());
        }
        return response;
    }


    @Override
    public ResponseEntity<String> executeScrypt(User user) {
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

        result.append(this.getAllUsers());
        result.append(this.createUser(createdUser).getBody());
        result.append(this.updateUser(3L, updatedUser).getBody());
        result.append(this.deleteUserById(3L).getBody());

        System.out.println("Result code = '" + result + "'");
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

//            if (!this.authToken.equals(jSessionIdReceived)) {
//            System.out.println("\n\t\t*** old value 'JSESSIONID' = " + authToken.getJSessionId());
//            authToken.setJSessionId(jSessionIdReceived);
//            System.out.println("\t\t*** new value 'JSESSIONID'  = " + authToken.getJSessionId());
//        } else {
//            System.out.println("\n\t\t*** current value 'JSESSIONID' = " + authToken.getJSessionId());
//        }
    }

//    private void refreshSessionId(HttpHeaders headers) {
//        String jSessionIdReceived = headers.get("Set-Cookie")
//                .stream()
//                .filter(cookie -> cookie.startsWith("JSESSIONID"))
//                .findFirst()
//                .orElse("null")
//                .replace("JSESSIONID=", "")
//                .split(";")
//                [0];
//        if (!this.authToken.equals(jSessionIdReceived)) {
//            System.out.println("\n\t\t*** old value 'JSESSIONID' = " + authToken.getJSessionId());
//            authToken.setJSessionId(jSessionIdReceived);
//            System.out.println("\t\t*** new value 'JSESSIONID'  = " + authToken.getJSessionId());
//        } else {
//            System.out.println("\n\t\t*** current value 'JSESSIONID' = " + authToken.getJSessionId());
//        }
//    }
}



