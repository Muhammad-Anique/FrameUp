package com.example.FrameUpServer.Controller;
import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/society/delete/{sid}")
    public String deleteSocietyById(@PathVariable int sid){
        societyDao.delete(sid);
        return "Deleted";
    }


    @GetMapping("/society/{sid}")
    public Society getSociety(@PathVariable int sid)
    {
        return societyDao.getSocietyById(sid);
    }

    @GetMapping("/society/head/{sName}")
    public Society getSocietyByName(@PathVariable String sName)
    {
        return societyDao.getSocietyByName(sName);
    }

    @DeleteMapping("/society/whole/delete/{sid}")
    public String deleteMembersAndOperatives(@PathVariable int sid){
        return societyDao.deleteMembersAndOperatives(sid);
    }

    @GetMapping("/society/who-is-this/{sid}/{roll}")
    public int whoIsThis(@PathVariable int sid, @PathVariable String roll){
        return societyDao.whoIsThis(sid, roll);
    }

    @GetMapping("/society/is-head/{roll}/{sid}")
    public int isHead(@PathVariable String roll, @PathVariable int sid){
        return societyDao.isHead(roll,sid);
    }

    @GetMapping("/society/is-head/{roll}")
    public int isHeadAnySociety(@PathVariable String roll){
        return societyDao.isHeadAnyone(roll);
    }
}