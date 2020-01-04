package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByUserId(String userid);
    List<User> findAllById(long id);

}
