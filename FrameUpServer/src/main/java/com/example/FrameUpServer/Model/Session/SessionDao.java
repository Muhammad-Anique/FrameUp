package com.example.FrameUpServer.Model.Session;

import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionDao {

    @Autowired
    SessionRepository sessionRepository;

    public Session save(Session session)
    {
        return sessionRepository.save(session);
    }
    public String getSessionRoll(){
        return sessionRepository.getSession();
    }
}
