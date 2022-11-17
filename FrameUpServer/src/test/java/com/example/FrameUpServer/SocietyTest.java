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

        society.setDateCreated("16-11-22");
        society.setSocietyCategory("Entertainment");
        society.setSocietyHead("Ali");
        society.setSocietyMotive("Refreshment");
        society.setSocietyName("Music");
        society.setSocietyMembers(10);

        societyDao.save(society);

    }
}
