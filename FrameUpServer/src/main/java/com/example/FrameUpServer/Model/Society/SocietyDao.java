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
    public void delete (Society society)
    {
       repository.delete(society);
    }
    public List<Society> getAllSociety()
    {
        List<Society> societies=new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(societies::add);
        return societies;
    }



}
