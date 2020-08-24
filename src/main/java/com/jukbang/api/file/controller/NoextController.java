package com.jukbang.api.file.controller;

import com.jukbang.api.file.service.ImagePathService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class NoextController {

    private ImagePathService imagePathService;

    @CrossOrigin(origins = "*")
    @PostMapping("/ext/{Univid}/{Roomid}/{Imgid}/{filename}")
    public void write(@PathVariable("Univid") int Univid, @PathVariable("Roomid") int Roomid, @PathVariable("Imgid") int Imgid, @PathVariable("filename") String filename) {
        imagePathService.SaveimagePath(Univid, Roomid, Imgid, filename);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/roomimg/{Univid}/{roomid}/{Imgid}")
    public String load(@PathVariable("Univid") int Univid, @PathVariable("roomid") int Roomid, @PathVariable("Imgid") int Imgid) {
        return imagePathService.LoadimagePath(Univid, Roomid, Imgid);
    }
}
