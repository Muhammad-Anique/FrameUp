package com.example.FrameUpServer;

import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.event.Event;
import com.example.FrameUpServer.Model.event.EventDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventTests {
    @Autowired
    private EventDao eventDao;

   // @Test
    void addEvent()
    {
        Event event=new Event();
        event.setEventSubject("Freelancing");
        event.setDateEventCreated("18-10-22");
        event.setEventType("Informative");
        event.setSocietyRelated("Career Counselling");
        event.setAuthorName("Ali Afzal");
        event.setEventMedia("Lahore News");
        event.setEventText("Hi Guys.....Welcome to FreeLancing");

        eventDao.save(event);
    }
   @Test
    void getAllEvent()
    {
        List<Event> event=eventDao.getAllEvents();
        GlobalFunctions fg = new GlobalFunctions();
        fg.PrintStart();
        System.out.println(event);
        fg.PrintEnd();

    }
    //@Test
    void getAllEventsAndDeleteThem()
    {
        List<Event> events=eventDao.getAllEvents();
        for (Event event:events)
        {
            eventDao.delete(event);
        }
    }






}
