package com.example.demo.service;


import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.util.Objects;
import java.util.StringTokenizer;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ImagePathService {
    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public String SaveimagePath(int Univid, int Roomid, int Imageid, String filename){

            Image image;
        if (imageRepository.findByUnividAndRoomidAndImageid(Univid,Roomid,Imageid)== null)
            image = new Image();
        else
            image = imageRepository.findByUnividAndRoomidAndImageid(Univid,Roomid,Imageid);
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
        return "/ext/"+image.getFilename();
    }
}
