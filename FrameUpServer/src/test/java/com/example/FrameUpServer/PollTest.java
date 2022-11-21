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


        Poll poll =new Poll();
        poll.setCreatedBy("20l-2171");
        poll.setPollStatement("Which is society is best?");
        poll.setSocietyRelated("General");
        poll.setNoOfResponses(20);
        poll.setTotalOptions(5);
        poll.setPollOption1("Music");
        poll.setOption1Responses(20);
        poll.setPollOption2("Arts");
        poll.setOption2Responses(0);
        poll.setPollOption3("Softec");
        poll.setOption3Responses(0);
        poll.setPollOption4("Python");
        poll.setOption4Responses(0);
        poll.setPollOption5("Javascript");
        poll.setOption5Responses(0);
        pollDao.savePoll(poll);

        System.out.println("**************8");
        System.out.println(pollDao.getAllPolls());

    }


}
