package com.jukbang.api.community.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.room.dto.BoardlistDto;
import com.jukbang.api.community.dto.CommunityDto;
import com.jukbang.api.community.service.CommunityService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommunityController {


    private CommunityService communityService;

    @CrossOrigin(origins = "*")
    @GetMapping("community/{Univid}")
    public List list(Model model) {
        List<BoardlistDto> boardList = communityService.getCommunitylist();
        return boardList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("community/{Univid}/{Postid}")
    public CommunityDto Post(@PathVariable("Univid") int Univid, @PathVariable("Postid") Long Postid) {
        CommunityDto postdata = communityService.getPost(Univid, Postid);
        return postdata;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("community/{Univid}")
    public Long write(@PathVariable("Univid") int Univid, @RequestBody String json) throws JsonProcessingException {
        return communityService.SavePost(Univid, json);

    }

    @CrossOrigin(origins = "*")
    @PutMapping("community/{Univid}/{Postid}")
    public Long update(@PathVariable("Univid") int Univid, @PathVariable("Postid") Long Postid, @RequestBody String json) throws JsonProcessingException {
        return communityService.rewritePost(Univid, Postid, json);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("community/{Univid}/{Postid}")
    public String delete(@PathVariable("Univid") int Univid, @PathVariable("Postid") Long Postid) {
        communityService.deletePost(Postid);
        return "success";
    }

}
