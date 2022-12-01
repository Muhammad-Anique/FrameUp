package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperative;
import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperativeDao;
import com.example.FrameUpServer.Model.poll.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocietyOperativeController {
    @Autowired
    SocietyOperativeDao societyOperativeDao;
    @GetMapping("/society-operative/{roll}")
    public SocietyOperative getSocietyOperativeByRoll(@PathVariable String roll){
        return societyOperativeDao.getSocietyOperativeByRoll(roll);
    }
    @PostMapping("/society-operative/save")
    public SocietyOperative save(@RequestBody SocietyOperative societyOperative){
        return societyOperativeDao.save(societyOperative);
    }
    @GetMapping("/society-operative/{sid}/{type}")
    public List<SocietyOperative> getSocietyOperativeByRoll(@PathVariable int sid, @PathVariable int type){
        return societyOperativeDao.getOperativeFromType(type,sid);
    }

    @GetMapping("/society-operative/is-advisor/{type}")
    public int isAdvisorByEmail(@PathVariable int type, @RequestBody String email){
     return societyOperativeDao.isAdvisorByEmail(type,email);
    }

    @GetMapping("/society-operative/advisors")
    public List<String> getAdvisorfromop(){
        return societyOperativeDao.getAdvisorsFromOperative();
    }
}
