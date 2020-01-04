package com.example.demo.repository;

import com.example.demo.model.RecommandFilter;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecommandFilterRepository extends JpaRepository<RecommandFilter,Long> {
    Optional<RecommandFilter> findByUnivid(Long Univid);
}
