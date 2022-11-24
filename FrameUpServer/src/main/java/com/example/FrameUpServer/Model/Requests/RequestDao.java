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

    public List<Request> getAllRequestByRoll(){

        List<Request> req = new ArrayList<>();
        Streamable.of(requestRepository.findAll())
                .forEach(req::add);
        return req;
    }
}
