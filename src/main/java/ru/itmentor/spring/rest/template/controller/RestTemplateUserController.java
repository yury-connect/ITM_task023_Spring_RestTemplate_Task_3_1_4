package ru.itmentor.spring.rest.template.controller;

import ru.itmentor.spring.rest.template.model.User;


public interface RestTemplateUserController {

    User create (ru.itmentor.spring.rest.template.model.User user);
    User get (ru.itmentor.spring.rest.template.model.User user);
    User update (ru.itmentor.spring.rest.template.model.User user);
    User delete (ru.itmentor.spring.rest.template.model.User user);
}
