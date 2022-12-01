package com.example.FrameUpServer.Model.Requests;

import com.example.FrameUpServer.Model.SocietyOperative.SocietyOperative;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends CrudRepository<Request,Integer> {
    @Query(value = "select * from request r where send_to = :roll",nativeQuery = true)
   List<Request> retrieveRequestByRollSendTo(@Param("roll") String roll);

    @Query(value = "select count(*) from request r where r.send_by = :roll and r.request_type= :type and r.society_id=:sid",nativeQuery = true)
    int getReqTypeByRoll(@Param("roll") String roll,@Param("type") String type, @Param("sid") int sid);

}
