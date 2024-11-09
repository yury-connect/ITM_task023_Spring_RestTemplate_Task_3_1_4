package ru.itmentor.spring.rest.template.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import static ru.itmentor.spring.rest.template.constants.Constants.JSESSIONID_DEFAULT;


@Data
@AllArgsConstructor
public class AuthToken {

    // JSESSIONID
    private String jSessionId;


    public AuthToken() {
        this.jSessionId = JSESSIONID_DEFAULT;
    }

}