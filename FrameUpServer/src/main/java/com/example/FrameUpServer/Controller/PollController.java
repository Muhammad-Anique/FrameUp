package com.example.FrameUpServer.Controller;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import com.example.FrameUpServer.Model.poll.Poll;
import com.example.FrameUpServer.Model.poll.PollDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PollController {

    @Autowired
    private PollDao pollDao;

    @PostMapping("/poll/save")
    public Poll savePoll(@RequestBody Poll poll) {
        return pollDao.savePoll(poll);
    }

    @GetMapping("/poll/get-all")
    public List<Poll> getAllPoll()
    {
        return pollDao.getAllPolls();
    }

    @GetMapping("/poll/{pollId}")
    public Poll getPollByPollId(@PathVariable String pollId){
        return pollDao.getPollByPollId(pollId);
    }

}
