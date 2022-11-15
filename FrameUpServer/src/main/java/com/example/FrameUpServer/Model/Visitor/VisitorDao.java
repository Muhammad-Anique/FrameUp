package com.example.FrameUpServer.Model.Visitor;

import com.example.FrameUpServer.Model.EmailServices.SendEmail;
import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorDao {

    @Autowired
    private VisitorRepository visitorRepository;
    @Autowired
    private SendEmail sendEmail;

    public Visitor saveVisitor(Visitor visitor) {
        Visitor V = visitorRepository.save(visitor);
        sendingEmailtoUVpersons();
        return V;
    }

    private void sendingEmailtoUVpersons() {
        List<String> S = getemailofuvperson();
        for (int i = 0; i < S.size(); i++) {
            System.out.println(S.get(i));
            String Otp = getOTPByEmail(S.get(i));
            System.out.println(Otp);
            sendEmail.sendTheEmail(Otp,S.get(i));
        }
    }

    public List<Visitor> getAllVisitor()
    {
        List<Visitor> visitor = new ArrayList<>();
        Streamable.of(visitorRepository.findAll())
                .forEach(visitor::add);
        return visitor;
    }

    public List<String> getemailofuvperson()
    {
        List<String> emails = new ArrayList<>();
        Streamable.of(visitorRepository.retrieveVisitorByEmailNotSent()).forEach(emails::add);
        return emails;
    }

    public String getOTPByEmail(String email)
    {
        return visitorRepository.retrieveOTPbyEmail(email);
    }
    public Visitor getVisitorByRoll(String roll)
    {
        return visitorRepository.retrieveVisitorByRoll_rp(roll);
    }



}
