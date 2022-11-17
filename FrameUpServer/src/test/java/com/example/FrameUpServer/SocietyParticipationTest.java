package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipation;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipationDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SocietyParticipationTest {

    @Autowired
    private SocietyParticipationDao societyParticipationDao;

    @Test
    void saveSocietyParticipation() {
        SocietyParticipation society = new SocietyParticipation();

        society.setRating(0);
        society.setParticipationId(1);
        society.setRollNo("20L-1047");

        societyParticipationDao.save(society);

    }
}