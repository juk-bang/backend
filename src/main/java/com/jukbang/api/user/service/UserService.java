package com.jukbang.api.user.service;

import com.jukbang.api.user.dto.UserDto;
import com.jukbang.api.user.entity.User;
import com.jukbang.api.user.repository.UserRepository;
import com.jukbang.api.user.request.CreateUserRequest;
import com.jukbang.api.user.request.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * user정보 출력
     */
    @Transactional
    public List<UserDto> getUserList(String userId) {
        List<User> boardEntities = userRepository.findAllByUserId(userId);
        List<UserDto> boardDtoList = new ArrayList<>();

        for (User boardEntity : boardEntities) {
            UserDto boardDTO = UserDto.builder()
                    .id(boardEntity.getId())
                    .userId(boardEntity.getUserId())
                    .univId(boardEntity.getUnivId())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }

    /**
     * 회원 정보 수정 하기
     * 댓글의 고유번호 (id) 에 접근하여 수정
     */
    @Transactional
    public void rewriteUser(long id, UpdateUserRequest updateUserRequest) {
        userRepository.save(
                new User(
                        id,
                        updateUserRequest.getUserId(),
                        null,
                        updateUserRequest.getUnivid(),
                        null,
                        null
                )); // 잘모르겠음
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
