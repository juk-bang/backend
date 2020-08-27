package com.jukbang.api.chat.controller;

import com.jukbang.api.chat.request.CreateChatRoomRequest;
import com.jukbang.api.chat.request.SendMessageRequest;
import com.jukbang.api.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message/{userId}")
public class MessageController {

    private final MessageService messageService;

    /**
     * message list GET
     *
     * @param userId     대학가 지역 ID
     * @param chatRoomId
     * @return (List) messageList
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/{chatRoomId}")
    public List getMessageList(
            @PathVariable("userId") String userId,
            @PathVariable("chatRoomId") long chatRoomId
    ) {
        return messageService.getMessageList(userId, chatRoomId);
    }

    /**
     * chat room 만들기 CREATE
     *
     * @param userId                대학가 지역 ID
     * @param createChatRoomRequest
     * @return (List) chatRoomId
     */
    @CrossOrigin(origins = "*")
    @PostMapping("")
    public long createMessage(
            @PathVariable("userId") String userId,
            @RequestBody CreateChatRoomRequest createChatRoomRequest
    ) {
        return messageService.MakeChatRoom(userId, createChatRoomRequest);
    }

    /**
     * message 보내기  UPDATE
     * (chatRoomId 수정하기)
     *
     * @param userId             대학가 지역 ID
     * @param chatRoomId
     * @param sendMessageRequest
     * @return
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/{chatRoomId}")
    public long sendMessage(
            @PathVariable("userId") String userId,
            @PathVariable("chatRoomId") long chatRoomId,
            @RequestBody SendMessageRequest sendMessageRequest
    ) {
        return messageService.SendMessage(userId, chatRoomId, sendMessageRequest);
    }

    /**
     * 메시지 삭제 DELETE
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
