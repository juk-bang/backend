package com.example.demo.repository;

import com.example.demo.model.Comments;
import com.example.demo.model.Community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    List<Community> findAllByWriter(String writer);
}
