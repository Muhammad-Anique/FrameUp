package com.example.FrameUpServer.Model.ChatBox;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatBoxRepository extends CrudRepository<ChatBox, Integer> {

    //Queries
    @Query(value = "select * from chatBox where chatBox.chatBoxId = :Id",nativeQuery = true)
    ChatBox retrieveChatBoxById_rp(@Param("Id") String chatBoxID);
}
