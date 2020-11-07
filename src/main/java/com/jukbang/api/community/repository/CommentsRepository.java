package com.jukbang.api.community_student.repository;

import com.jukbang.api.community_student.entity.Comments;
import com.jukbang.api.community_student.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByPost(Post post);

    Optional<Comments> findByCommentsId(Long commentsId);
}
