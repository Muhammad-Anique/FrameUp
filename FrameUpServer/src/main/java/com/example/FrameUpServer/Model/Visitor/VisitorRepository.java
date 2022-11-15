package com.example.FrameUpServer.Model.Visitor;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
    @Query(value = "select * from person where person.roll_no = :rollNumber",nativeQuery = true)
    Visitor retrieveVisitorByRoll_rp(@Param("rollNumber") String roll);

    @Query(value = "select p.email from person p where p.account_status like 'uv'",nativeQuery = true)
    List<String> retrieveVisitorByEmailNotSent();

    @Query(value = "select p.otp from person p where p.email = :email",nativeQuery = true)
    String retrieveOTPbyEmail(@Param("email") String email);
}
