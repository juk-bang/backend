package com.jukbang.api.repository;

import com.jukbang.api.model.MessageChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageChatRoomRepository extends JpaRepository<MessageChatRoom, Long> {
    //List<Message> findAllByUser(String sender);
}