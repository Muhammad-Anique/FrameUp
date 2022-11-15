package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SocietyTest {

    @Autowired
    private SocietyDao societyDao;

    @Test
    void addSocietyTest()
    {
        Society society=new Society();

        society.setDateCreated("15-11-22");
        society.setSocietyCategory("Intellectual");
        society.setSocietyHead("Afzal");
        society.setSocietyMotive("Skills Improvement");
        society.setSocietyName("Career Counselling");

        societyDao.save(society);

    }
}
