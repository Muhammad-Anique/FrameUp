package com.example.FrameUpServer.Model.event;

<<<<<<< HEAD
=======
import com.example.FrameUpServer.Model.Person.Person;
>>>>>>> 8af41bb10b71714214dd57d03fcea077060eb459
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

    public void save (Event event)
    {
        repository.save(event);
    }
    public void delete(Event event)
    {
        repository.delete(event);
    }
<<<<<<< HEAD
    public List<Event> getAllEvents()
    {
        List<Event> events=new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(events::add);
        return events;
=======

    public List<Event> getAllEvents()
    {
        List<Event> event = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(event::add);
        return event;
>>>>>>> 8af41bb10b71714214dd57d03fcea077060eb459
    }








}
