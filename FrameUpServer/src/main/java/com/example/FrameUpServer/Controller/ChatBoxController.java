package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.ChatBox.ChatBox;
import com.example.FrameUpServer.Model.ChatBox.ChatBoxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatBoxController {

    @Autowired
    private ChatBoxDao chatBoxDao;

    @GetMapping("/chatBox/get-all")
    public List<ChatBox> getAllChatBoxes()
    {
        return chatBoxDao.getAllChatBoxes();
    }
    @PostMapping("/chatBox/save")
    public ChatBox save(@RequestBody ChatBox C)
    {
        return chatBoxDao.saveChatBox(C);
    }
}
