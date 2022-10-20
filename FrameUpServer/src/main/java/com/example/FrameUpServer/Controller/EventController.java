package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.event.Event;
import com.example.FrameUpServer.Model.event.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventDao eventDao;

    @GetMapping("/event/get-all")
    public List<Event> getaAllEvent()
    {
        return eventDao.getAllEvents();
    }
    @PostMapping("/event/save")
    public Event save (@RequestBody Event event)
    {
        return eventDao.save(event);
    }


}
