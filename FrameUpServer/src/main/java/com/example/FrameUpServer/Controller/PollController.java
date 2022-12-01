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
    public Poll getPollByPollId(@PathVariable int pollId){
        return pollDao.getPollByPollId(pollId);
    }

    @PutMapping("/poll/update/{pollId}")
    public Poll addPollResponse(@PathVariable int pollId,@RequestBody Poll poll){
        Poll p = pollDao.getPollByPollId(pollId);
        p.setOption1Responses(poll.getOption1Responses());
        p.setOption2Responses(poll.getOption2Responses());
        p.setOption3Responses(poll.getOption3Responses());
        p.setOption4Responses(poll.getOption4Responses());
        p.setOption5Responses(poll.getOption5Responses());
        p.setNoOfResponses(poll.getOption1Responses() + poll.getOption2Responses() +
                poll.getOption3Responses() + poll.getOption4Responses()+ poll.getOption5Responses());
        return pollDao.savePoll(p);
    }

    @DeleteMapping("/poll/delete/{id}")
    public String deleteThePoll(@PathVariable int id){
        return pollDao.DeletePoll(id);
    }


}
