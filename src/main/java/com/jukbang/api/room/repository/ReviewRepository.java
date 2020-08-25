package com.jukbang.api.room.repository;

import com.jukbang.api.room.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUnivIdAndRoomId(int univId, int roomId);
}
