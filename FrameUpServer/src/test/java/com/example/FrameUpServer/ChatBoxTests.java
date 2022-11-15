package com.example.FrameUpServer;

import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.ChatBox.ChatBox;
import com.example.FrameUpServer.Model.ChatBox.ChatBoxDao;
import com.example.FrameUpServer.Model.Message.Message;
import com.example.FrameUpServer.Model.Message.MessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChatBoxTests {

    @Autowired
    private ChatBoxDao chatBoxDao;

    @Test
    void chatBoxSavingTest() {
        ChatBox chatBox = new ChatBox();
        chatBox.setChatBoxData(1, 1);
        chatBoxDao.saveChatBox(chatBox);
    }

    //@Test
    void getally()
    {
        List<ChatBox> C = chatBoxDao.getAllChatBoxes();
        GlobalFunctions gb = new GlobalFunctions();
        gb.PrintStart();
        System.out.println(C);
        gb.PrintEnd();
    }

    //@Test
    void GetAllChatBoxes()
    {
        List<ChatBox> C = chatBoxDao.getAllChatBoxes();
        System.out.println(C);
    }
}
