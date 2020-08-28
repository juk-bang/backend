package com.jukbang.api.chat;

import com.jukbang.api.chat.request.CreateChatRoomRequest;
import com.jukbang.api.chat.service.MessageService;
import com.jukbang.api.common.BaseControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
public class CreateMessage extends BaseControllerTest {

    @Autowired
    private MessageService messageService;

    @Test
    @WithMockUser("TestUser1")
    @DisplayName("메시지 방 만들기 (성공)")
    void createMessageSuccess() throws Exception{
        CreateChatRoomRequest createChatRoomRequest = CreateChatRoomRequest.builder()
                .chatRoom(1)
                .buyer("buyer")
                .seller("seller")
                .build();



        this.mockMvc.perform(RestDocumentationRequestBuilders.post("/message/{userId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(createChatRoomRequest))
        )

                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("CreateChatRoom"))
        ;
    }
}
