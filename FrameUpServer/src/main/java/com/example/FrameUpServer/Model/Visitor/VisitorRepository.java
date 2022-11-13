package com.example.FrameUpServer.Model.Visitor;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    @Query(value = "select * from person where person.roll_no = :rollNumber",nativeQuery = true)
    Visitor retrieveVisitorByRoll_rp(@Param("rollNumber") String roll);
}
