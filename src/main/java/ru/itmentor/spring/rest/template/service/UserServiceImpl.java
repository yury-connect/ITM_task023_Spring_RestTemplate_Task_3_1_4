package ru.itmentor.spring.rest.template.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.AuthToken;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.repositor.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static ru.itmentor.spring.rest.template.constants.Constants.SOURCE_URL;


/*
Этот слой вызывает RestTemplate, обрабатывает данные и взаимодействует с внешними API, если нужно.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final RestTemplate restTemplate;
    private final AuthToken authToken;



    // ПЕРЕСМОТРЕТЬ!!!
    @Override
    public User createUser(User user) {
        return restTemplate.postForObject(SOURCE_URL, user, User.class);
//        return userRepository.createUser(user);
    }

    @Override
    public List<User> createUsers(List<User> userList) {
        List<User> createdUsers = userRepository.createUsers(userList);
        return createdUsers;
    }

    // ПЕРЕСМОТРЕТЬ!!!
    @Override
    public User findUserById(int id) {
//        String url = SOURCE_URL + "/" + id;
//        return restTemplate.getForObject(url, User.class);
        return userRepository.findUserById(id);
    }


    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findUserByUsername(userName);
    }



















    // **********   1   **********
    @Override
    public List<User> findAllUsers() {
//        final String url = "http://codeflex.co:8080/rest/Management/login";
//        RestTemplate template = new RestTemplate();
//        Credentials cred = new Credentials();
//        cred.setUserName("admin@codeflex.co");
//        cred.setPassword("godmode");
//        HttpEntity<Credentials> request = new HttpEntity<>(cred);

        HttpEntity<String> request = new HttpEntity<>(authToken.getJwtToken());

//        HttpEntity<String> response = template.exchange(url, HttpMethod.POST, request, String.class);

        HttpEntity<User[]> response = restTemplate.exchange(SOURCE_URL, HttpMethod.GET, request, User[].class);
        HttpHeaders headers = response.getHeaders(); // для получения всех заголовков, которые пришли вместе с ответом.
        authToken.setJwtToken(headers.getFirst(HttpHeaders.SET_COOKIE));




        User[] users = restTemplate.getForObject(SOURCE_URL, User[].class);
        return Arrays.asList(users);
//        return userRepository.findAllUsers();
    }














    @Override
    public User updateUser(User user) {
//        String url = SOURCE_URL + "/" + user.getId();
//        restTemplate.put(url, user);
        return userRepository.updateUser(user);
    }

    @Override
    public User deleteUserById(int id) {
//        String url = SOURCE_URL + "/" + id;
//        restTemplate.delete(url);
        return userRepository.deleteUserById(id);
    }

    @Override
    public User deleteAllUsers() {
        return userRepository.deleteAllUsers();
    }



    // **********************************************************************
//    @GetMapping(value = "/set-cookie")
    private ResponseEntity<?> setCookie(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("JSESSIONID", authToken.getJwtToken());

        cookie.setPath("/");
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        response.setContentType("text/plain");
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

//    @GetMapping(value = "/get-cookie")
    public ResponseEntity<?> readCookie(@CookieValue(value = "data") String data) {
        return ResponseEntity.ok().body(data);
    }

}



