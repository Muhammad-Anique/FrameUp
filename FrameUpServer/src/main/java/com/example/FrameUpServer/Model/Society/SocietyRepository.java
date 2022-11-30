package com.example.FrameUpServer.Model.Society;

import com.example.FrameUpServer.Model.Requests.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocietyRepository extends CrudRepository<Society,Integer>
{
    @Query(value = "select * from society s where society_id = :sid",nativeQuery = true)
    Society getSocietyByID(@Param("sid") int sid);

    @Query(value = "select * from society where society_head = :sName order by society_id desc limit 1",nativeQuery = true)
    Society getSocietyByName(@Param("sName") String sName);

    @Query(value = "delete from society_operative where society_id = :sid;\n" + "delete from society_participation where society_id = :sid ; select 'true' " , nativeQuery = true)
    String deleteMembersAndOperatives(@Param("sid") int sid);

    @Query(value = "Call whoIsThis(:sid , :rollNo);", nativeQuery = true)
    int whoIsThis(@Param("sid") int sid, @Param("rollNo") String rollNo);

    @Query(value = "Select count(*) from society_operative so where so.operative_roll = :rollNo and so.operative_type=1;",nativeQuery = true)
    int isHead(@Param("rollNo") String rollNo);

}
