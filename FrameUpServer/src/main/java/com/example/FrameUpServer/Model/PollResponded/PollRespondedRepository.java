package com.example.FrameUpServer.Model.PollResponded;

import com.example.FrameUpServer.Model.poll.Poll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PollRespondedRepository extends CrudRepository<PollResponded,Integer> {
    @Query(value = "select * from poll_responded p where p.roll_no = :rollNumber",nativeQuery = true)
    PollResponded retrievePollRespondedById(@Param("rollNumber") String rollNumber);
}
