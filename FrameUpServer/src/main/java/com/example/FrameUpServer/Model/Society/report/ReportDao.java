package com.example.FrameUpServer.Model.Society.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportDao {
    @Autowired
    private ReportRepository repository;

    public Report save (Report report)
    {
        return repository.save(report);
    }
    public void delete(Report report)
    {
        repository.delete(report);
    }
    public List<Report>getAllReport()
    {
        List<Report> reports=new ArrayList<>();
        Streamable.of(repository.findAll()).forEach(reports::add);
        return reports;

    }
    public float calculateMembers(int NoOfFemales,int NoOfMales)
    {
        return NoOfFemales+NoOfMales;
    }




}
