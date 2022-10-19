package com.example.FrameUpServer.Model.event;

import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD

public interface EventRepository extends CrudRepository<Event,Integer> {
=======
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
>>>>>>> 8af41bb10b71714214dd57d03fcea077060eb459
}
