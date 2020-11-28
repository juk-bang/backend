package com.jukbang.api.room.service;

import com.jukbang.api.user.entity.embedded.Filter;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {

  private final UserRepository userRepository;

  public Filter getMyFilter(String requestUserId) {
    return userRepository.findByUserId(requestUserId).orElseThrow(UserNotFoundException::new)
        .getFilter();
  }
}
