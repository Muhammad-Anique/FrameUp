package com.example.FrameUpServer.Model.SocietyOperative;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocietyOperativeRepository extends CrudRepository<SocietyOperative,Integer> {
    @Query(value = "select * from society_operative o where o.operative_roll = :roll",nativeQuery = true)
    SocietyOperative getSocietyOperativeByRoll(@Param("roll") String roll);

    @Query(value = "select * from society_operative o where o.operative_type=:type and o.society_id=:sid",nativeQuery = true)
    List<SocietyOperative> getOperativeFromType(@Param("type") int type, @Param("sid") int sid);

}
