package com.example.FrameUpServer.Model.report;

import com.example.FrameUpServer.Model.SocietyParticipation.SocietyParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportDao {
    @Autowired
    private ReportRepository repository;
    @Autowired
    private SocietyParticipationRepository societyRepository;

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
    public long countSocietyMembers(String societyName)
    {
        return societyRepository.retrieveMemberCountBySocietyName(societyName);

    }
    public long countMaleSocietyMembers(String societyName)
    {
        return societyRepository.retrieveMaleGenderCountByRollNo(societyName);
    }
    public long countFemaleSocietyMembers(String societyName)
    {
        return societyRepository.retrieveFemaleGenderCountByRollNo(societyName);
    }
    public float calculateAverageRating(String societyName)
    {
        return societyRepository.retrieveAverageRatingOfSociety(societyName);
    }




}
