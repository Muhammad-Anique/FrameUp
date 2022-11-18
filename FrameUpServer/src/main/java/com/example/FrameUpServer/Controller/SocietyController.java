package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class SocietyController {

    @Autowired
    SocietyDao societyDao;

    @GetMapping("/Society/get_societyName")
    public List<String> getAllSocietyName()
    {
        return societyDao.getSocietyNameList();
    }
}
