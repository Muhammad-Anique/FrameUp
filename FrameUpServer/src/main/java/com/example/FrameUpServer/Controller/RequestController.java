package com.example.FrameUpServer.Controller;

import com.example.FrameUpServer.Model.Requests.Request;
import com.example.FrameUpServer.Model.Requests.RequestDao;
import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperative;
import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperativeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    RequestDao requestDao;

    @GetMapping("/requests/{roll}")
    public List<Request> getRequestsByRoll(@PathVariable String roll){
        return requestDao.getAllRequestByRollSendTo(roll);
    }
    @PostMapping("/requests/save")
    public Request save(@RequestBody Request request){
        return requestDao.save(request);
    }

    @DeleteMapping("/requests/delete/{reqId}")
    public void deleteRequest(@PathVariable int reqId){
        requestDao.deleteRequest(reqId);

    }

    @GetMapping("/request/{roll}/{type}/{sid}")
    public int getRequestTypeByRoll(@PathVariable String roll,@PathVariable String type,  @PathVariable int sid){
        return requestDao.getRequestTypeByRoll(roll,type,sid);
    }


}
