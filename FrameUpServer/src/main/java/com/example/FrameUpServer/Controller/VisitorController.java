package com.example.FrameUpServer.Controller;
import com.example.FrameUpServer.Model.EmailServices.EmailSenderService;
import com.example.FrameUpServer.Model.EmailServices.SendEmail;
import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Person.PersonDao;
import com.example.FrameUpServer.Model.Visitor.Visitor;
import com.example.FrameUpServer.Model.Visitor.VisitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitorController {
    @Autowired
    private VisitorDao visitorDao;

    @GetMapping("/visitor/get-all")
    public List<Visitor> getAllVisitor()
    {
        return visitorDao.getAllVisitor();
    }

    @PostMapping("/visitor/save")
    public Visitor save(@RequestBody Visitor visitor) {
        int i = (int) (Math.random() * 10000);
        if(i<1000) i=i+1000;
        String otp = Integer.toString(i);
        visitor.setOTP(otp);
        return visitorDao.saveVisitor(visitor);
    }

    @PutMapping("/visitor/{roll}/update/status")
    public Visitor updateStatus(@PathVariable String roll){
       Visitor V =  visitorDao.getVisitorByRoll(roll);
       V.setIsVerified(true);
       V.setAccountStatus("Active");
       return visitorDao.saveVisitor(V);
    }


    @PutMapping("/visitor/{roll}/update/pic")
    public Visitor updateProfilePic(@PathVariable String roll,@RequestBody String url ){
        Visitor V =  visitorDao.getVisitorByRoll(roll);
        V.setProfileUrl(url);
        return visitorDao.saveVisitor(V);
    }


    @GetMapping("/visitor/{roll}")
    public Visitor getVisitorByRoll(@PathVariable String roll){
        return visitorDao.getVisitorByRoll(roll);
    }

    @GetMapping("/visitor/otp/{roll}")
    public String getOTPByRoll(@PathVariable String roll){
        char[] e = new char[7];
        e[0] ='l';
        e[1] = roll.charAt(0);
        e[2] = roll.charAt(1);
        e[3] = roll.charAt(4);
        e[4] = roll.charAt(5);
        e[5] = roll.charAt(6);
        e[6] = roll.charAt(7);
        String mail = new String(e);
        String EmailAddress = mail.concat("@lhr.nu.edu.pk");
        System.out.println(EmailAddress);
        return visitorDao.getOTPByEmail(EmailAddress);
    }

    @PutMapping("/visitor/status/update/{roll}/{st}")
    public Visitor updateStatusOfVisitor(@PathVariable String roll,@PathVariable String st){
        Visitor V =  visitorDao.getVisitorByRoll(roll);
        if(st.compareTo("inactive")==0){
        V.setIsVerified(false);}
        else{
            V.setIsVerified(true);
        }
        V.setAccountStatus(st);
        return visitorDao.saveVisitor(V);
    }
}
