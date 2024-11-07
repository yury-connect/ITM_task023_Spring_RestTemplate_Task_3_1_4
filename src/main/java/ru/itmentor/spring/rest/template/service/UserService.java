package ru.itmentor.spring.rest.template.service;
import ru.itmentor.spring.rest.template.model.User;


public interface UserService {

    User create (User user);
    User get (User user);
    User update (User user);
    User delete (User user);
}
