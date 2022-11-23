package com.example.FrameUpServer.Controller;
import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocietyController {
    @Autowired
    private SocietyDao societyDao;

    @GetMapping("/society/get-all")
    public List<Society> getAllSociety()
    {
        return societyDao.getAllSociety();
    }
    @PostMapping("/society/save")
    public Society save(@RequestBody Society society){
        return societyDao.save(society);
    }

}