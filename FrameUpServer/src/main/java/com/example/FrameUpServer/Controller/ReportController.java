package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Society.report.Report;
import com.example.FrameUpServer.Model.Society.report.ReportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public Report save (@RequestBody Report report)
    {
        return reportDao.save(report);
    }

}
