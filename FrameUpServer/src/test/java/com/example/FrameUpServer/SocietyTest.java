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
    void mem(){
        System.out.println("the memtype = " + societyDao.whoIsThis(17,"20l-2171"));

    }
    //@Test
    void addSocietyTest()
    {
        Society society=new Society();
        society.setSocietyBackground("url");
        society.setSocietyDescription("This is Softec society and its description is this");
        society.setSocietyLikes(0);
        society.setSocietyTagline("Lets do art together");
        society.setSocietyName("Arts Society");
        society.setSocietyRating(0);
        societyDao.save(society);

    }
}
