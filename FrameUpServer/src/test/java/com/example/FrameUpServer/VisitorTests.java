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

    @Test
    public void VisitorSavingTest()
    {
        int num = (int)(Math.random()*1000000);
        String otp =Integer.toString(num);
        Gender gen = Gender.Male;

        Visitor visitor = new Visitor();
        visitor.set_Name_Email_Roll("Muhamamd Akbar","l202020@lhr.nu.edu.pk","20l-2020");
        visitor.setGender(gen);
        visitor.setOTP(otp);
        visitor.setAccountStatus("Active");
        visitor.setIsVerified(false);
        visitor.setPassword("123456");
        visitor.setJoiningDate("20-Nov-2020");
        visitor.setPhoneNumber("03364274008");
        GlobalFunctions gb = new GlobalFunctions();

        gb.PrintStart();
        System.out.println(visitorDao.saveVisitor(visitor));
        gb.PrintEnd();

    }
}
