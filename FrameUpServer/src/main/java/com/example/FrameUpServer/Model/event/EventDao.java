package com.example.FrameUpServer.Model.event;
import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventDao {

    @Autowired
    private EventRepository repository;

    public Event save (Event event)
    {
       return repository.save(event);
    }
    public void delete(Event event)
    {
        repository.delete(event);
    }


    public List<Event> getAllEvents()
    {

        List<Event> event = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(event::add);
        return event;

    }

}
