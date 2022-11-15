package com.example.FrameUpServer;

import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.Message.Message;
import com.example.FrameUpServer.Model.Message.MessageDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageTests {

    @Autowired
    private MessageDao messageDao;

    @Test
    void messageSavingTest() {
        Message message = new Message();
        message.setMessageData(1, "HI!", "l200928", "l200957");
        messageDao.saveMessages(message);
    }

    //@Test
    void getally()
    {
        List<Message> M = messageDao.getAllMessages();
        GlobalFunctions gb = new GlobalFunctions();
        gb.PrintStart();
        System.out.println(M);
        gb.PrintEnd();
    }

    //@Test
    void GetAllPersons()
    {
        List<Message> M = messageDao.getAllMessages();
        System.out.println(M);
    }
}
