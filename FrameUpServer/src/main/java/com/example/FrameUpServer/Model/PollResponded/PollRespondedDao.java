package com.example.FrameUpServer.Model.PollResponded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollRespondedDao {
    @Autowired
    PollRespondedRepository pollRespondedRepository;

    public PollResponded savePoll(PollResponded pollResponded){
        return pollRespondedRepository.save(pollResponded);
    }
    public PollResponded getPollRespondedByRoll(String Roll){
        return pollRespondedRepository.retrievePollRespondedById(Roll);
    }
}
