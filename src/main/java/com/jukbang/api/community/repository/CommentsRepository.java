package com.jukbang.api.community.repository;

import com.jukbang.api.community.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByPostId(Long postId);

    Optional<Comments> findByPostIdAndCommentsId(Long postId, Long commentsId);
}
