package com.jukbang.api.file.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Integer> {
    Optional<ThumbnailImage> findByFileId(String imageId);
}
