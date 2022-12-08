package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Society.Society;
import com.example.FrameUpServer.Model.Society.SocietyDao;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipation;
import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocietyParticipationController {

    @Autowired
    private SocietyParticipationDao societyParticipationDao;

    @GetMapping("/society-participation/get-all")
    public List<SocietyParticipation> getAllSociety() {
        return societyParticipationDao.getAllSocietyParticipation();
    }

    @PostMapping("/society-participation/save")
    public SocietyParticipation save(@RequestBody SocietyParticipation societyParticipation) {
        return societyParticipationDao.save(societyParticipation);
    }

    @GetMapping("/society-participation/get-all/count/{sid}")
    public int getMemberCountBySID(@PathVariable int sid) {
        return societyParticipationDao.getMemberCountBySocietyId(sid);
    }

    @GetMapping("/society-participation/get-all/rolls/{sid}")
    public List<String> getMemberListBySID(@PathVariable int sid) {
        return societyParticipationDao.getSocietyMembersByRoll(sid);
    }

    @GetMapping("/society-participation/{roll}")
    public List<SocietyParticipation> getMemberParticipationByRoll(@PathVariable String roll) {
        return societyParticipationDao.getMemberExistence(roll);
    }

    @GetMapping("/society-participation/rating/{sid}")
    public float getRatingBySocietyId(@PathVariable int sid) {
        return societyParticipationDao.getSocietyRating(sid);
    }

    @PutMapping("/society-participation/update/rating/{sid}/{roll}")
    public SocietyParticipation getRatingBySocietyId(@PathVariable int sid, @PathVariable String roll, @RequestBody SocietyParticipation sopi) {
        SocietyParticipation sp = societyParticipationDao.getParticipationByRollAndSid(sid, roll);
        sp.setRating(sopi.getRating());
        return societyParticipationDao.save(sp);
    }


    @GetMapping("/society-participation/get-exist/{sid}/{roll}")
    public int getSocietyPByRollAndSid(@PathVariable int sid, @PathVariable String roll) {
        return societyParticipationDao.getSocietyParticipationByRollAndSid(sid, roll);
    }


    @DeleteMapping("/society-participation/delete/{sid}/{roll}")
    public String deleteParticipation(@PathVariable int sid, @PathVariable String roll) {
        return societyParticipationDao.deleteMember(sid, roll);
    }
}
