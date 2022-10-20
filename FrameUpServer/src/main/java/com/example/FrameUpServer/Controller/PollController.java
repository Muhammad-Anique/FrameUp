package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.poll.Poll;
import com.example.FrameUpServer.Model.poll.PollDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PollController {
    @Autowired
    private PollDao pollDao;

    @GetMapping("/poll/get-all")
    public List<Poll>getAllPoll()
    {
        return pollDao.getAllPoll();
    }

    @PostMapping ("/poll/save")
    public Poll save(@RequestBody Poll poll)
    {
         return pollDao.save(poll);
    }
}
