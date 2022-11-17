package com.example.FrameUpServer.Model.SocietyParticipation;

import com.example.FrameUpServer.Model.Visitor.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyParticipationRepository extends CrudRepository<SocietyParticipation, Integer > {

   @Query( value="SELECT count(society_name) FROM society_participation WHERE society_participation.society_name=:societyName",nativeQuery = true)
   long retrieveMemberCountBySocietyName(@Param("societyName")String sName );

   @Query( value="SELECT COUNT(*),gender FROM person JOIN society_participation ON person.roll_no=society_participation.roll_no\n" +
           "WHERE gender=1 AND society_participation.society_name=:societyName",nativeQuery = true)
   long retrieveFemaleGenderCountByRollNo(@Param("societyName")String sName );

   @Query( value="SELECT COUNT(*),gender FROM person JOIN society_participation ON person.roll_no=society_participation.roll_no\n" +
           "WHERE gender=1 AND society_participation.society_name=:societyName",nativeQuery = true)
   long retrieveMaleGenderCountByRollNo(@Param("societyName")String sName );

   @Query(value="SELECT AVG(rating) FROM society_participation WHERE society_name=:societyName",nativeQuery = true)
   float retrieveAverageRatingOfSociety (@Param("societyName")String sName);

}
