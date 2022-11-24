package com.example.FrameUpServer.Model.SocietyParticipation;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyParticipationRepository extends CrudRepository<SocietyParticipation, Integer > {

    @Query(value = "select count(*) from society_participation sp where sp.society_id =:sid",nativeQuery = true)
    int getsocietymembercount(@Param("sid") int sid);

    @Query(value = "select sp.roll_no from society_participation sp where sp.society_id =:sid",nativeQuery = true)
    List<String> getSocietyMemberRoll(@Param("sid") int sid);
}