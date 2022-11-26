package com.example.FrameUpServer.Model.report;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends CrudRepository<Report,Integer> {
    @Query(value="call MakeTheReportNew(:sid,:subject,:conclusion,:body,:type,:rDate);",nativeQuery = true)
    String makeReport(@Param("sid") int sid, @Param("subject") String subject,@Param("conclusion") String conclusion
            ,@Param("body") String body,@Param("type") String type,@Param("rDate") String rDate);

}