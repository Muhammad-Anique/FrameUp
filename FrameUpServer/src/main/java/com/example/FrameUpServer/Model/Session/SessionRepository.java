package com.example.FrameUpServer.Model.Session;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends CrudRepository<Session, Integer > {
    @Query(value = "select sp.roll_no from society_participation sp where sp.society_id =:sid",nativeQuery = true)
    String getSession();
}
