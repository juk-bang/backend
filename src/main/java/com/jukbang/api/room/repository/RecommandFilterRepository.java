package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.RecommandFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommandFilterRepository extends JpaRepository<RecommandFilter, Long> {
    Optional<RecommandFilter> findByUnivid(Long Univid);
}
