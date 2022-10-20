package com.example.FrameUpServer.Model.poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollDao {

    @Autowired PollRepository repository;

    public Poll save (Poll poll)
    {
       return repository.save(poll);
    }
    public void delete (Poll poll)
    {
        repository.delete(poll);
    }
    public List<Poll> getAllPoll()
    {
        List<Poll> polls=new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(polls::add);

        return polls;
    }


}
