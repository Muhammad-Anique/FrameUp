package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperativeDao;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipation;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipationDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SocietyParticipationTest {

    @Autowired
    SocietyParticipationDao societyParticipationDao;

    @Test
    void AddMember()
    {
        SocietyParticipation societyParticipation =new SocietyParticipation();
        societyParticipation.setParticipationId(9);
        societyParticipation.setRollNo("20l-2171");
        societyParticipation.setSocietyId(1);
        societyParticipation.setRating(0);
        societyParticipationDao.save(societyParticipation);

    }
}
