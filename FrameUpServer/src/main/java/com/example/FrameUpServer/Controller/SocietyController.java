package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Person.Person;
import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocietyController {
    @Autowired
    private SocietyDao societyDao;
    @GetMapping("/society/get-all")
    public List<Society> getAllSoc_()
    {
        return societyDao.getAllSociety();
    }
    @GetMapping("/Society/{name}")
    public Society getSocietyByName_(@PathVariable String name)
    {
        return societyDao.getSocietyByName(name);
        //For Int Path variables (Integer.parseInt(id));
    }

}
