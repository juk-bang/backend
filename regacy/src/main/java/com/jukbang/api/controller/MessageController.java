
package com.jukbang.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.dto.MessageDto;
import com.jukbang.api.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
public class MessageController {


    private MessageService messageService;


    /**
     * 한 chatroom 에서의 메시지 리스트 출력
     * <p>
     * ex)
     * chatroom1 1)
     * user1이 보낸 메시지   (hi )
     * user2가 보낸 메시지   (hello )
     * user1이 보낸 메시지   (how are u? )
     * ...
     * <p>
     * <p>
     * list 에 표시되야할 정보 : sender (보낸이), body(내용), (제목), score(점수)
     */
    @CrossOrigin(origins = "*")
    @GetMapping("message/{userid}/{chatroom}")
    public List Post(@PathVariable("userid") String userid, @PathVariable("chatroom") long chatroom) {
        List<MessageDto> postdata = messageService.getMessageList(userid, chatroom);
        return postdata;
    }


    /**
     * 채팅방 만들기
     * 입력되야할 정보 : user2 (받는이)
     */
    @CrossOrigin(origins = "*")
    @PostMapping("message/{userid}")
    public long write(@PathVariable("userid") String userid, @RequestBody String json) throws JsonProcessingException {
        return messageService.MakeChatRoom(userid, json);

    }

    /**
     * 쪽지 보내기
     * 입력되야할 정보 : body (메시지 내용)
     */
    @CrossOrigin(origins = "*")
    @PostMapping("message/{userid}/{chatroom}")
    public long Send(@PathVariable("userid") String userid, @PathVariable("chatroom") long chatroom, @RequestBody String json) throws JsonProcessingException {
        return messageService.SendMessage(userid, chatroom, json);

    }


    /**
     * 댓글 삭제 기능
     * 댓글의 고유번호 (id) 에 접근하여 삭제
     * (univid, postid 상관없이)
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("message/{userid}/{id}")
    public String delete(@PathVariable("id") long id) {
        messageService.DeleteMessage(id);
        return "success";
    }

}
