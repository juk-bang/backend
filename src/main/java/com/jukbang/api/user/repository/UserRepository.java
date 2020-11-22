package com.jukbang.api.user.repository;

import com.jukbang.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserId(String userid);

    Optional<User> findByUserId(String userId);

    Optional<User> findByUserIdAndRefreshToken(String userId, String refreshToken);

    void deleteByUserId(String userId);
}
