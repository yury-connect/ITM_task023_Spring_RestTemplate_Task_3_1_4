package ru.itmentor.spring.rest.template.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthToken {

    private String jwtToken;

}