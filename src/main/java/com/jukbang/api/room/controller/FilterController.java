package com.jukbang.api.room.controller;

import com.jukbang.api.room.entity.UnivFilter;
import com.jukbang.api.room.service.FilterService;
import com.jukbang.api.user.entity.embedded.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FilterController {

  private final FilterService filterService;

  @GetMapping("/user/filter")
  public Filter getMyFilter(){
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
    return filterService.getMyFilter(requestUserId);
  }

  @GetMapping("/rooms/filter/{univId}")
  public UnivFilter getRecommendFilter(@PathVariable Long univId){
    return filterService.getRecommendFilter(univId);
  }
}
