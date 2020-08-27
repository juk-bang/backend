package com.jukbang.api.chat;

import com.jukbang.api.chat.request.CreateChatRoomRequest;
import com.jukbang.api.chat.request.SendMessageRequest;
import com.jukbang.api.chat.service.MessageService;
import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteMessage extends BaseControllerTest {
    @Autowired
    private MessageService messageService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("메시지 삭제하기 (성공)")
    void deleteMessageSuccess() throws Exception{
        CreateChatRoomRequest createChatRoomRequest = CreateChatRoomRequest.builder()
                .chatRoom(1)
                .buyer("buyer")
                .seller("seller")
                .build();

        long chatRoom =messageService.MakeChatRoom("userId",createChatRoomRequest);

        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .id(1)
                .sender("sender")
                .receiver("receiver")
                .body("body")
                .chatRoom(1)
                .isRead(1)
                .build();

        messageService.SendMessage("userId",1,sendMessageRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/message/{userId}/{chatRoomId}","userId",chatRoom))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("DeleteChat"))
        ;
    }
}
