package com.example.FrameUpServer.Model.event;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
