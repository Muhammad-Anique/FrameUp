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

    @Query(value = "select * from society s where society_head = :sName",nativeQuery = true)
    Society getSocietyByName(@Param("sName") String sName);
}
