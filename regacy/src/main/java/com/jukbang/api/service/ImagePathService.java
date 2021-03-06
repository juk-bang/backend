package com.jukbang.api.service;


import com.jukbang.api.model.Image;
import com.jukbang.api.repository.ImageRepository;
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
        if (imageRepository.findByUnividAndRoomidAndImageid(Univid, Roomid, Imageid) == null)
            image = new Image();
        else
            image = imageRepository.findByUnividAndRoomidAndImageid(Univid, Roomid, Imageid);
        image.setUnivid(Univid);
        image.setRoomid(Roomid);
        image.setImageid(Imageid);
        image.setFilename(filename);
        System.out.println(image.getFilename());
        imageRepository.save(image);
        System.out.println(image.getRoomid());
        return filename;
    }

    @Transactional
    public String LoadimagePath(int Univid, int Roomid, int Imageid) {
        Image image = imageRepository.findByUnividAndRoomidAndImageid(Univid, Roomid, Imageid);
        return "/ext/" + image.getFilename();
    }
}
