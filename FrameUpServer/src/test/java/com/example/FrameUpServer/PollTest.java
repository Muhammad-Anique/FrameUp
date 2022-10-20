package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.poll.Poll;
import com.example.FrameUpServer.Model.poll.PollDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PollTest {

    @Autowired
    private PollDao pollDao;

    @Test
    void addPollTest()
    {
        Poll poll=new Poll();
        poll.setPollSubject("Voting");
        poll.setPollType("Binary");
        poll.setDatePollCreated("20-10-22");
        poll.setCreatedBy("Ali Afzal");
        poll.setNoOfResponses(12);
        poll.setPollText("Come and vote for you favourite society");
        poll.setPollOptions("vote or don't vote");

        pollDao.save(poll);

    }


}
