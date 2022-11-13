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
        sendEmail.sendTheEmail(visitor.getOTP(),visitor.getEmail());
        return visitorRepository.save(visitor);
    }
    public List<Visitor> getAllVisitor()
    {
        List<Visitor> visitor = new ArrayList<>();
        Streamable.of(visitorRepository.findAll())
                .forEach(visitor::add);
        return visitor;
    }

    public Visitor getVisitorByRoll(String roll)
    {
        return visitorRepository.retrieveVisitorByRoll_rp(roll);
    }

}
