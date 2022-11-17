package com.example.FrameUpServer.Model.Society;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyDao {

    @Autowired
    private SocietyRepository societyRepository;

    public Society save(Society society)
    {
        return societyRepository.save(society);
    }
    public void delete (Society society)
    {
       societyRepository.delete(society);
    }
    public List<Society> getAllSociety()
    {
        List<Society> societies=new ArrayList<>();
        Streamable.of(societyRepository.findAll()).forEach(societies::add);
        return societies;
    }
    public Society getSocietyByName(String name)
    {
        return societyRepository.ret
    }


}
