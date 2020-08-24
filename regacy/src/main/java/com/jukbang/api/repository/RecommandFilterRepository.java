package com.jukbang.api.repository;

import com.jukbang.api.model.RecommandFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommandFilterRepository extends JpaRepository<RecommandFilter, Long> {
    Optional<RecommandFilter> findByUnivid(Long Univid);
}
