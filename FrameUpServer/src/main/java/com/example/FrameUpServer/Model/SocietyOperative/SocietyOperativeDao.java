package com.example.FrameUpServer.Model.SocietyOperative;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyOperativeDao {

    @Autowired
    SocietyOperativeRepository societyOperativeRepository;
    public SocietyOperative save(SocietyOperative societyOperative){
        return societyOperativeRepository.save(societyOperative);
    }
    public SocietyOperative getSocietyOperativeByRoll(String roll){
        return societyOperativeRepository.getSocietyOperativeByRoll(roll);
    }

    public List<SocietyOperative> getOperativeFromType(int type, int sid){
        List<SocietyOperative> societyOperatives = new ArrayList<>();
       Streamable.of(societyOperativeRepository.getOperativeFromType(type,sid)).forEach(societyOperatives::add);
       return societyOperatives;
    }

    public int isAdvisorByEmail(int oType, String email){
        return societyOperativeRepository.isAdvisorByEmail(oType,email);
    }

    public List<String> getAdvisorsFromOperative(){
       return societyOperativeRepository.getAdvisorFromOperative();
    }


}
