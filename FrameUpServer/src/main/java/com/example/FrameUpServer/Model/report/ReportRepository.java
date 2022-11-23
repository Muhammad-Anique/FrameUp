package com.example.FrameUpServer.Model.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report,Integer> {

    @Query(value="SELECT COUNT(society_participation.roll_no) FROM society_participation  WHERE society_participation.roll_no LIKE :batchNo% AND society_participation.society_name=:societyName",nativeQuery = true)

    long retrieveMembersByBatch(@Param("batchNo")int batchNumber,@Param("societyName") String societyName);

    @Query(value="SELECT COUNT( society_participation.roll_no) FROM person JOIN society_participation ON person.roll_no=society_participation.roll_no WHERE society_participation.roll_no In (SELECT society_participation.roll_no FROM society_participation WHERE society_participation.roll_no LIKE :batchNo% AND society_participation.society_name=:societyName)AND gender=:gender",nativeQuery = true)
    long retrieveGenderByBatch(@Param("batchNo")int batchNo, @Param("societyName") String societyName, @Param("gender")int gender);


}
