package com.example.FrameUpServer.Model.poll;

import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll,Integer> {
    @Query(value = "select * from poll where poll.poll_id = :pollNumber",nativeQuery = true)
    Poll retrievePollById(@Param("pollNumber") int pollNumber);

}
