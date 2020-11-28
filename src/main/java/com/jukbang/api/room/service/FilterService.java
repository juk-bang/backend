package com.jukbang.api.room.service;

import com.jukbang.api.room.entity.UnivFilter;
import com.jukbang.api.room.repository.UnivFilterRepository;
import com.jukbang.api.user.entity.embedded.Filter;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {

  private final UserRepository userRepository;
  private final UnivFilterRepository univFilterRepository;

  public Filter getMyFilter(String requestUserId) {
    return userRepository.findByUserId(requestUserId).orElseThrow(UserNotFoundException::new)
        .getFilter();
  }

  public UnivFilter getRecommendFilter(Long univId) {
    return univFilterRepository.findById(univId).orElse(new UnivFilter());
  }
}
