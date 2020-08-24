
package com.jukbang.api.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jukbang.api.chat.dto.MessageDto;
import com.jukbang.api.chat.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * import com.example.demo.repository.CommentsRepository;
 * import org.springframework.ui.Model;
 **/


@RestController
@AllArgsConstructor
@RequestMapping("message/{userid}")
public class MessageController {

    private MessageService messageService;


    /**
     * message list GET
     *
     * @param userid
     * @param chatroom
     * @return (List) messageList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{chatroom}")
    public List getMessageList(
            @PathVariable("userid") String userid,
            @PathVariable("chatroom") long chatroom
    ) {
        List<MessageDto> messageList = messageService.getMessageList(userid, chatroom);
        return messageList;
    }


    /**
     * chat room 만들기 CREATE
     *
     * @param userid
     * @param json
     * @return (List) chatroom
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public long createMessage(
            @PathVariable("userid") String userid,
            @RequestBody String json
    ) throws JsonProcessingException {
        return messageService.MakeChatRoom(userid, json);

    }


    /**
     * message 보내기  UPDATE
     * (chatroom 수정하기)
     *
     * @param userid
     * @param chatroom
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{chatroom}")
    public long updateMessage(
            @PathVariable("userid") String userid,
            @PathVariable("chatroom") long chatroom,
            @RequestBody String json
    ) throws JsonProcessingException {
        return messageService.SendMessage(userid, chatroom, json);

    }

    /**
     * 댓글 삭제 DELETE
     *
     * @param id
     * @return (String) success
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable("id") long id
    ) {
        messageService.DeleteMessage(id);
        return "success";
    }

}
