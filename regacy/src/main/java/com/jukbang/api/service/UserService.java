package com.jukbang.api.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jukbang.api.dto.UserDto;
import com.jukbang.api.model.User;
import com.jukbang.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.hibernate.criterion.Example;
 * import java.util.Optional;
 **/


@AllArgsConstructor
@Service

public class UserService {
    private UserRepository userRepository;

    /**
     * user정보 출력
     */
    @Transactional
    public List<UserDto> getUserList(String userid) {
        List<User> boardEntities = userRepository.findAllByUserId(userid);
        List<UserDto> boardDtoList = new ArrayList<>();

        for (User boardEntity : boardEntities) {
            UserDto boardDTO = UserDto.builder()
                    .id(boardEntity.getId())
                    .userId(boardEntity.getUserId())
                    .univid(boardEntity.getUnivid())
                    .build();

            boardDtoList.add(boardDTO);
        }

        return boardDtoList;
    }

    /**
     * 회원 정보 임의 입력
     * 입력해야될 데이터 : writter (작성자), body(내용)
     */
    @Transactional
    public long SaveUser(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto;
        userDto = objectMapper.readValue(json, UserDto.class);


        UserDto userDTO = UserDto.builder()
                .userId(userDto.getUserId())
                .univid(userDto.getUnivid())
                .build();

        return userRepository.save(userDTO.toEntity()).getId(); // 잘모르겠음
    }


    /**
     * 회원 정보 수정 하기
     * 댓글의 고유번호 (id) 에 접근하여 수정
     */
    @Transactional
    public void rewriteUser(long id, String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto;
        userDto = objectMapper.readValue(json, UserDto.class);
        userDto.setId(id);
        userRepository.save(userDto.toEntity()).getId();
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
