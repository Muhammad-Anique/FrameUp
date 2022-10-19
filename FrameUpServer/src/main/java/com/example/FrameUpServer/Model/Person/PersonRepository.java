package com.example.FrameUpServer.Model.Person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    //Queries
    @Query(value = "select * from person where person.roll_no = :rollNumber",nativeQuery = true)
    Person retrievePersonByRoll_rp(@Param("rollNumber") String roll);
<<<<<<< HEAD

    //Query2

=======
>>>>>>> 8af41bb10b71714214dd57d03fcea077060eb459


}
