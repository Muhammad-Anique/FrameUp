package com.example.FrameUpServer.Model.Society;

import com.example.FrameUpServer.Model.Person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SocietyRepository extends CrudRepository<Society,Integer>
{
    @Query(value = "select * from person where Society.societyName = :name",nativeQuery = true)
    Society retrieveSocietyByName(@Param("name") String name);

}
