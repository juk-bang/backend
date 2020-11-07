package com.jukbang.api.community.repository;

import com.jukbang.api.community.CommunityRole;
import com.jukbang.api.community.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByRole(CommunityRole role);

    List<Post> findAllByWriter(String writer);

    Optional<Post> findByPostId(Long postId);


}
