package com.jukbang.api.chat;

import com.jukbang.api.chat.request.CreateChatRoomRequest;
import com.jukbang.api.chat.service.MessageService;
import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class GetMessageList extends BaseControllerTest {

    @Autowired
    private MessageService messageService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("메시지 리스트 가져오기 (성공)")
    void GetMessageListSuccess() throws Exception {
        CreateChatRoomRequest createChatRoomRequest = CreateChatRoomRequest.builder()
                .chatRoom(1)
                .buyer("buyer")
                .seller("seller")
                .build();

        long chatroom = messageService.MakeChatRoom("userId",createChatRoomRequest);

        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/message/{userId}/{chatRoomId}","userId",chatroom))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("GetMessageList"))
        ;

    }

}
