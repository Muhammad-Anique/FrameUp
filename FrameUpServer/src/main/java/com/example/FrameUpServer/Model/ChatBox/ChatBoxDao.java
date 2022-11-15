package com.example.FrameUpServer.Model.ChatBox;

import com.example.FrameUpServer.Model.Contact.Contact;
import com.example.FrameUpServer.Model.Contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBoxDao {

    @Autowired
    private ChatBoxRepository chatBoxRepository;

    public ChatBox saveChatBox(ChatBox chatBox){
        return chatBoxRepository.save(chatBox);
    }

    public List<ChatBox> getAllChatBoxes()
    {
        List<ChatBox> chatBoxes = new ArrayList<>();
        Streamable.of(chatBoxRepository.findAll())
                .forEach(chatBoxes::add);
        return chatBoxes;
    }


}
