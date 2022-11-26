package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.PollResponded.PollResponded;
import com.example.FrameUpServer.Model.PollResponded.PollRespondedDao;
import com.example.FrameUpServer.Model.poll.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PollRespondedController {

    @Autowired
    PollRespondedDao pollRespondedDao;

    @PostMapping("/poll-responded/save")
    public PollResponded savePoll(@RequestBody PollResponded pollResponded) {
        return pollRespondedDao.savePoll(pollResponded);
    }

    @GetMapping("/poll-responded/{roll}")
    public PollResponded getAllPollRespondedByRoll(@PathVariable("roll") String roll)
    {
        return pollRespondedDao.getPollRespondedByRoll(roll);
    }
}
