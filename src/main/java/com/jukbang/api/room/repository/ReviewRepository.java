package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.Review;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByIdAndWriter_UserId(long reviewId, String userId);

    List<Review> findAllByRoomId(long roomId);

    long countByRoomId(long roomId);
}
