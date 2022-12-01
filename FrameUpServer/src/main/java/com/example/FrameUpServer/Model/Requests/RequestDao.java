package com.example.FrameUpServer.Model.Requests;

import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestDao {
    @Autowired
    RequestRepository requestRepository;

    public Request save(Request r){
        return requestRepository.save(r);
    }

    public List<Request> getAllRequest(){
        List<Request> req = new ArrayList<>();
        Streamable.of(requestRepository.findAll())
                .forEach(req::add);
        return req;
    }

    public List<Request> getAllRequestByRollSendTo(String Roll){
        List<Request> req = new ArrayList<>();
        Streamable.of(requestRepository.retrieveRequestByRollSendTo(Roll))
                .forEach(req::add);
        return req;
    }

    public void deleteRequest(int ReqID){
      requestRepository.deleteById(ReqID);
    }

    public int getRequestTypeByRoll(String rollNo, String type, int sid){
        return requestRepository.getReqTypeByRoll(rollNo, type,sid);
    }


}
