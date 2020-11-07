package com.jukbang.api.user.controller;

import com.jukbang.api.user.service.FavoriteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/favorites")
@RequiredArgsConstructor
public class FavoriteController {

  private final FavoriteService favoriteService;

  /**
   * favorite list 조회 GET
   *
   * @return (List) favoriteList
   */
  @GetMapping
  public List getFavoriteList() {
      String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

      return favoriteService.getFavoriteList(requestUserId);
  }

  /**
   * 찜 목록 추가 CREATE
   *
   * @param roomId
   * @return (long) id
   */
  @PostMapping("/{roomId}")
  public long createFavorite(
      @PathVariable("roomId") long roomId
  ) {
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

    return favoriteService.SaveFavorite(roomId, requestUserId);
  }

  /**
   * 찜 삭제 DELETE
   *
   * @param roomId
   * @return (String) success
   */
  @DeleteMapping("/{roomId}")
  public void deleteFavorite(
      @PathVariable("roomId") long roomId
  ) {
      String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();

      favoriteService.DeleteFavorite(roomId, requestUserId);
  }
}
