package com.example.FrameUpServer;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
        society.setSocietyHead("ALI");
        society.setSocietyMotive("Skills Improvement");
        society.setSocietyName("IEEE");

        societyDao.save(society);

    }
    //@Test
    void deleteSociety()
    {
        List<Society> society = societyDao.getAllSociety();
        for (Society society1 : society)
            societyDao.delete(society1);;
    }
}
