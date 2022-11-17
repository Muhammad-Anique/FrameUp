package com.example.FrameUpServer.Model.SocietyParticipation;

import com.example.FrameUpServer.Model.Society.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocietyParticipationDao {

    @Autowired
    private SocietyParticipationRepository repository;
    public SocietyParticipation save(SocietyParticipation society)
    {
        return repository.save(society);
    }
}
