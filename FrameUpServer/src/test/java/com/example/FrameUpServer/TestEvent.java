package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Person.PersonDao;
import com.example.FrameUpServer.Model.event.Event;
import com.example.FrameUpServer.Model.event.EventDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootTest
public class TestEvent {

    @Autowired
    private EventDao eventDao;

    @Test
    void getty()
    {
        List<Event> ev = eventDao.getAllEvents();
        System.out.println(ev);
    }
}
