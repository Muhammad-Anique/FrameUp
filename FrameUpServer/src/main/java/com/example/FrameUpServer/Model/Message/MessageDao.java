package com.example.FrameUpServer.Model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageDao {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessages(Message messages){
        return messageRepository.save(messages);
    }

    public List<Message> getAllMessages()
    {
        List<Message> messages = new ArrayList<>();
        Streamable.of(messageRepository.findAll())
                .forEach(messages::add);
        return messages;
    }

}
