package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipation;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocietyParticipationController {

    @Autowired
    private SocietyParticipationDao societyParticipationDao;

    @GetMapping("/society-participation/get-all")
    public List<SocietyParticipation> getAllSociety()
    {
        return societyParticipationDao.getAllSocietyParticipation();
    }
    @PostMapping("/society-participation/save")
    public SocietyParticipation save(@RequestBody SocietyParticipation societyParticipation){
        return societyParticipationDao.save(societyParticipation);
    }

    @GetMapping("/society-participation/get-all/count/{sid}")
    public int getMemberCountBySID(@PathVariable int sid)
    {
        return societyParticipationDao.getMemberCountBySocietyId(sid);
    }



}
