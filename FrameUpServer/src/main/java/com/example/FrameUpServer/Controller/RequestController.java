//package com.example.FrameUpServer.Controller;
//
//import com.example.FrameUpServer.Model.Requests.RequestDao;
//import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperative;
//import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperativeDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class RequestController {
//
//    @Autowired
//    RequestDao requestDao;
//
//    @GetMapping("/society-operative/{roll}")
//    public SocietyOperative getSocietyOperativeByRoll(@PathVariable String roll){
//        return societyOperativeDao.getSocietyOperativeByRoll(roll);
//    }
//    @PostMapping("/society-operative/save")
//    public SocietyOperative save(@RequestBody SocietyOperative societyOperative){
//        return societyOperativeDao.save(societyOperative);
//    }
//
//}
