package com.example.FrameUpServer.Model.Society;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SocietyRepository extends CrudRepository<Society,Integer>
{
    @Query(value="SELECT society.society_name FROM society",nativeQuery = true)
    List<String> retrieveSocietyName();

}
