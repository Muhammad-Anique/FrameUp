package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.report.Report;
import com.example.FrameUpServer.Model.report.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class ReportController {
    @Autowired
    private ReportDao reportDao;

    @GetMapping("/report/get-all")
    public List<Report> getAllReport()
    {
        return reportDao.getAllReport();
    }

    @PostMapping("/report/save")
    public Report save (@RequestBody Report report)
    {
        return reportDao.save(report);
    }

    @GetMapping("/report/{societyName}")
    public long countSocietyMembers(@PathVariable String societyName )
    {
        return countSocietyMembers(societyName);
    }
    public long countMaleSocietyMembers(@PathVariable String societyName)
    {
        return countMaleSocietyMembers(societyName);
    }
    public long countFemaleSociety(@PathVariable String societyName)
    {
        return countFemaleSociety(societyName);
    }

    @RequestMapping(value="/report/{societyName}",method = GET)
    @ResponseBody
    public boolean getMemberInfo(@PathVariable String societyName)
    {
        long societyMemberCount= reportDao.countMaleSocietyMembers(societyName);
        long maleMemberCount=reportDao.countMaleSocietyMembers(societyName);
        long femaleMemberCount= reportDao.countFemaleSocietyMembers(societyName);

        return true;
    }






}
