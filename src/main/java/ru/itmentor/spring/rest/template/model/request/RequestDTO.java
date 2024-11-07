package ru.itmentor.spring.rest.template.model.request;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
//@Table(name = "users")
public class RequestDTO {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

//    @Column(name = "user_name", unique = true, nullable = false)
    private String name;

//    @Column(name = "user_lastName")
    private String lastName;

//    @Column(name = "user_age")
    private Byte age;

}
