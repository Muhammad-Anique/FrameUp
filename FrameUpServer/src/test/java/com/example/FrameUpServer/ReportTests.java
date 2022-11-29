//package com.example.FrameUpServer;
//
//import com.example.FrameUpServer.Model.report.Report;
//import com.example.FrameUpServer.Model.report.ReportDao;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class ReportTests {
//    @Autowired
//    private ReportDao reportDao;
//
//    @Test
//    void addReportTest()
//    {
//        Report report = new Report();
//
//        report.setReportAuthor("Laiba");
//        report.setDateReportCreated("14-11-22");
//        report.setNoOfFemales(20);
//        report.setNoOfMales(30);
//        report.setNoOfLikes(40);
//        report.setNoOfMembers(50);
//        report.setOverallRating(4);
//        report.setPopularity(4);
//        report.setReportBody("Hey Welcome to report Number 01");
//        report.setReportConclusion("Report concluded");
//        report.setReportSubject("Annual report");
//        report.setSocietyName("Career");
//
//        reportDao.save(report);
//    }
//}
