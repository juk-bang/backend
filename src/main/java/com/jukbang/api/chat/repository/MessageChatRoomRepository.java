package com.jukbang.api.chat.repository;

import com.jukbang.api.chat.entity.MessageChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageChatRoomRepository extends JpaRepository<MessageChatRoom, Long> {
    //List<Message> findAllByUser(String sender);
}