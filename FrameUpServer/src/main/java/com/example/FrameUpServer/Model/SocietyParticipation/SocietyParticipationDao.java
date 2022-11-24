package com.example.FrameUpServer.Model.SocietyParticipation;

import com.example.FrameUpServer.Model.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyParticipationDao {

    @Autowired
    private SocietyParticipationRepository repository;

    public SocietyParticipation save(SocietyParticipation society)
    {
        System.out.println("hi");
        return repository.save(society);
    }
    public List<SocietyParticipation> getAllSocietyParticipation()
    {
        List<SocietyParticipation> societyParticipation = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(societyParticipation::add);
        return societyParticipation;
    }

    public int getMemberCountBySocietyId(int sid){
        return repository.getsocietymembercount(sid);
    }

    public List<String> getSocietyMembersByRoll(int sid){
        return repository.getSocietyMemberRoll(sid);
    }


}