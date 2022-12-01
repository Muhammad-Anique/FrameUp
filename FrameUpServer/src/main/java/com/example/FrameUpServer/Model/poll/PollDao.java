package com.example.FrameUpServer.Model.poll;

import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollDao {

    @Autowired
    private PollRepository repository;

    public Poll savePoll(Poll poll)
    {
       return repository.save(poll);
    }

    public List<Poll> getAllPolls()
    {
        List<Poll> polls = new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(polls::add);
        return polls;
    }

    public Poll getPollByPollId(int pollid)
    {
        return repository.retrievePollById(pollid);
    }

    public String DeletePoll(int id){
        repository.deleteById(id);
        return "true";

    }
}
