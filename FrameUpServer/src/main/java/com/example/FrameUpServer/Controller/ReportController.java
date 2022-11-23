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

    @GetMapping("/report/{batchNo}/{societyName}")
    public long getMembersByBatch(@PathVariable int batchNo,@PathVariable String societyName)
    {
        return reportDao.getMembersByBatch(batchNo, societyName);
    }

    @GetMapping("/report/members/{societyName}")
    public long countSocietyMembers(@PathVariable String societyName )
    {
        return reportDao.countSocietyMembers(societyName);
    }
    @GetMapping("/report/male/{societyName}")
    public long countMaleSocietyMembers(@PathVariable String societyName)
    {
        return reportDao.countMaleSocietyMembers(societyName);
    }
    @GetMapping("/report/female/{societyName}")
    public long countFemaleSocietyMembers(@PathVariable String societyName)
    {
        return reportDao.countFemaleSocietyMembers(societyName);
    }

    @GetMapping("/report/Male/Batch/{batchNo}/{societyName}/{gender}")
    public long getMaleByBatch(@PathVariable int batchNo,@PathVariable String societyName, @PathVariable int gender)
    {
        return reportDao.getMaleMembersBtBatch(batchNo,societyName,gender);
    }


//    @GetMapping("/report/{societyName}")
//    public String getMemberInfo(@PathVariable String societyName)
//    {
//        long societyMemberCount= reportDao.countMaleSocietyMembers(societyName);
//        System.out.println(societyMemberCount);
//        long maleMemberCount=reportDao.countMaleSocietyMembers(societyName);
//        System.out.println(maleMemberCount);
//        long femaleMemberCount= reportDao.countFemaleSocietyMembers(societyName);
//        System.out.println(femaleMemberCount);
//        return "success";
//    }






}
