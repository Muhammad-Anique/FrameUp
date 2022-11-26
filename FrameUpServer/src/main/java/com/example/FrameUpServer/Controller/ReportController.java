package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.report.Report;
import com.example.FrameUpServer.Model.report.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String save(@RequestBody Report report)
    {
        return reportDao.save(report);
    }

    @GetMapping("/report/{sid}")
    public Report getReportBySID(@PathVariable int sid){
        return reportDao.getReportBySid(sid);
    }



}
