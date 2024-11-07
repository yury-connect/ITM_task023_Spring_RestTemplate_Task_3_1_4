package ru.itmentor.spring.rest.template.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String name;

    @Column(name = "user_lastName")
    private String lastName;

    @Column(name = "user_age")
    private Byte age;
//
//    @Column(name = "user_datestamp")
//    private Date dateStamp;
//
//    @Column(name = "user_timestamp")
//    private Time timeStamp;

}
