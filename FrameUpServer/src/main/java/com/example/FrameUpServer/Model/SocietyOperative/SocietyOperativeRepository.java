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


    @Query(value = "select count(*) from person p join society_operative so on p.roll_no =so.operative_roll where so.operative_type=:oType and p.email = :email",nativeQuery = true)
    int isAdvisorByEmail(@Param("oType") int oType, @Param("email") String email);


    @Query(value = "select so.operative_roll from society_operative so where so.operative_type=2 ",nativeQuery = true)
    List<String> getAdvisorFromOperative();

}
