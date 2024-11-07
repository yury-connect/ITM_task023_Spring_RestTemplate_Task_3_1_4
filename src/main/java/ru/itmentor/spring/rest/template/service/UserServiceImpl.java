package ru.itmentor.spring.rest.template.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.rest.template.model.User;
import ru.itmentor.spring.rest.template.repositor.UserRepository;

import java.util.Arrays;
import java.util.List;

import static ru.itmentor.spring.rest.template.constants.Constants.SOURCE_URL;


/*
Этот слой вызывает RestTemplate, обрабатывает данные и взаимодействует с внешними API, если нужно.
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;



    // ПЕРЕСМОТРЕТЬ!!!
    @Override
    public User createUser(User user) {
//        return restTemplate.postForObject(SOURCE_URL, user, User.class);
        return userRepository.createUser(user);
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

    // ПЕРЕСМОТРЕТЬ!!!
    @Override
    public List<User> findAllUsers() {
//        User[] users = restTemplate.getForObject(SOURCE_URL, User[].class);
//        return Arrays.asList(users);
        return userRepository.findAllUsers();
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
}
