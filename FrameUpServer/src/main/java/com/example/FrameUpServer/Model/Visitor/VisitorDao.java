package com.example.FrameUpServer.Model.Visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorDao {

    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
}
