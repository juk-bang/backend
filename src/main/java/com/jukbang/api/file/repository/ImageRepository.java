package com.jukbang.api.file.repository;

import com.jukbang.api.file.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByRoomIdAndImageId(int Roomid, int Imageid);
}
