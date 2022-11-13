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
    public Visitor save(@RequestBody Visitor V) {
        return visitorDao.saveVisitor(V);
    }

    @GetMapping("/visitor/{roll}")
    public Visitor getVisitorByRoll(@PathVariable String roll){
        return visitorDao.getVisitorByRoll(roll);
    }
}
