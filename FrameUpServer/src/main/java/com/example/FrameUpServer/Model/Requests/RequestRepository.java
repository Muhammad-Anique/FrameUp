package com.example.FrameUpServer.Model.Requests;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {
}
