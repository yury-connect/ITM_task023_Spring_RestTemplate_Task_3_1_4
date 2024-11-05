package ru.itmentor.spring.rest.template.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.rest.template.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {

}
