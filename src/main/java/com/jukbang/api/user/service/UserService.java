package com.jukbang.api.user.service;

import com.jukbang.api.user.dto.UserDto;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.exception.UserNotFoundException;
import com.jukbang.api.user.repository.UserRepository;
import com.jukbang.api.user.request.UpdateUserRequest;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  /**
   * user정보 출력
   */
  @Transactional
  public List<UserDto> getUserList(String userId) {
    List<User> boardEntities = userRepository.findAllByUserId(userId);
    List<UserDto> boardDtoList = new ArrayList<>();

    for (User boardEntity : boardEntities) {
      UserDto boardDTO = UserDto.builder()
          .userId(boardEntity.getUserId())
          .univId(boardEntity.getUnivId())
          .build();

      boardDtoList.add(boardDTO);
    }

    return boardDtoList;
  }

  /**
   * 회원 정보 수정 하기
   * 회원의 고유번호 (id) 에 접근하여 수정
   */
  @Transactional
  public void rewriteUser(long id,String userId, UpdateUserRequest updateUserRequest) {
    userRepository.findByAccountIdAndUserId(id,userId).orElseThrow(UserNotFoundException::new)
        .updatePassword(passwordEncoder.encode(updateUserRequest.getPassword()));
  }

  /**
   * 회원 삭제하기
   * 회원의 고유번호 (id) 에 접근하여 삭제
   */
  @Transactional
  public void deleteUser(long id) {
    userRepository.deleteById(id);
  }
}
