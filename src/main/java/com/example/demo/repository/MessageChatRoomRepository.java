package com.example.demo.repository;

 import com.example.demo.model.MessageChatRoom;
 import org.springframework.data.jpa.domain.Specification;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import java.util.List;

 @Repository
 public interface MessageChatRoomRepository extends JpaRepository<MessageChatRoom,Long> {
 //List<Message> findAllByUser(String sender);
 }