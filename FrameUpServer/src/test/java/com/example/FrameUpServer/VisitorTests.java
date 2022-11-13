package com.example.FrameUpServer;
import com.example.FrameUpServer.GlobalFunctions.GlobalFunctions;
import com.example.FrameUpServer.Model.Visitor.Gender;
import com.example.FrameUpServer.Model.Visitor.VisitorDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class VisitorTests {

    @Autowired
    private VisitorDao visitorDao;


    //@Test
    public void VisitorById()
    {
        Visitor v =  visitorDao.getVisitorByRoll("20l-2171");
        System.out.println(v);

    }
    @Test
    public void VisitorSavingTest()
    {
        int num = (int)(Math.random()*10000);
        String otp =Integer.toString(num);
        Gender gen = Gender.Male;
        Visitor visitor = new Visitor();
        visitor.set_Name_Email_Roll("Muhammad Khan Ghori","l202171@lhr.nu.edu.pk","20l-2120");
        visitor.setGender(gen);
        visitor.setOTP(otp);
        visitor.setAccountStatus("Active");
        visitor.setIsVerified(true);
        visitor.setPassword("112233");
        visitor.setJoiningDate("24-Nov-2020");
        visitor.setPhoneNumber("03364274908");
        GlobalFunctions gb = new GlobalFunctions();
        gb.PrintStart();
        System.out.println(visitorDao.saveVisitor(visitor));
        gb.PrintEnd();

    }
}
