package com.jukbang.api.community.repository;

import com.jukbang.api.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByWriter(String writer);
}
