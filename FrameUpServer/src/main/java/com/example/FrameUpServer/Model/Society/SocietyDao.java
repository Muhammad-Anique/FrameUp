package com.example.FrameUpServer.Model.Society;

import com.example.FrameUpServer.Model.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyDao {

    @Autowired
    private SocietyRepository repository;

    public Society save(Society society)
    {
        return repository.save(society);
    }
    public void delete (int sid)
    {
        repository.deleteById(sid);
    }
    public List<Society> getAllSociety()
    {
        List<Society> societies=new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(societies::add);
        return societies;
    }


    public Society getSocietyById(int sid){
        return repository.getSocietyByID(sid);
    }

    public Society getSocietyByName(String sName){
        return repository.getSocietyByName(sName);
    }

    public String deleteMembersAndOperatives(int sid){
        return repository.deleteMembersAndOperatives(sid);
    }

    public int whoIsThis(int sid, String rollNo){
        return repository.whoIsThis(sid,rollNo);
    }

    public int isHead(String roll, int sid){
        return repository.isHead(roll, sid);
    }
    public int isHeadAnyone(String roll){
        return repository.isHeadAny(roll);
    }
}
