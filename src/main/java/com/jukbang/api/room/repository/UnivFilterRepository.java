package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.UnivFilter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 방 필터 레포지터리
 *
 * @author always0ne
 * @version 1.0
 */
public interface UnivFilterRepository extends JpaRepository<UnivFilter, Long> {
}