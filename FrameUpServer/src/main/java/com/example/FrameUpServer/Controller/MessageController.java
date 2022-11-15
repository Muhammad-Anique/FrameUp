package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Message.Message;
import com.example.FrameUpServer.Model.Message.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @GetMapping("/message/get-all")
    public List<Message> getAllPersons()
    {
        return messageDao.getAllMessages();
    }
    @PostMapping("/message/save")
    public Message save(@RequestBody Message M)
    {
        return messageDao.saveMessages(M);
    }
}
