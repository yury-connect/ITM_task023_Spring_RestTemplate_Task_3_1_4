package ru.itmentor.spring.rest.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AuthToken {

    // JSESSIONID
    private String jSessionId;

}