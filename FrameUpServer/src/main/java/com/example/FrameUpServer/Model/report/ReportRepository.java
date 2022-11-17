package com.example.FrameUpServer.Model.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReportRepository extends CrudRepository<Report,Integer> {


   // @Query(value = "select societyMembers from Society where Society.societyName=:societyNamee  ")
}
    //SELECT count(society_name) FROM society_participation WHERE society_participation.society_name="Music";