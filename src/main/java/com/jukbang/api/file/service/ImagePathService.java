package com.jukbang.api.file.service;


import com.jukbang.api.file.entity.Image;
import com.jukbang.api.file.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ImagePathService {
    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public String SaveimagePath(int Univid, int Roomid, int Imageid, String filename) {

        Image image;
        if (imageRepository.findByRoomIdAndImageId(Roomid, Imageid) == null)
            image = new Image();
        else
            image = imageRepository.findByRoomIdAndImageId(Roomid, Imageid);
        image.setRoomId(Roomid);
        image.setImageId(Imageid);
        image.setFilename(filename);
        System.out.println(image.getFilename());
        imageRepository.save(image);
        System.out.println(image.getRoomId());
        return filename;
    }

    @Transactional
    public String LoadimagePath(int Univid, int Roomid, int Imageid) {
        Image image = imageRepository.findByRoomIdAndImageId(Roomid, Imageid);
        return "/ext/" + image.getFilename();
    }
}
