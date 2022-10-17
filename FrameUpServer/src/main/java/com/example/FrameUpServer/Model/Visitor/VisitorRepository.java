package com.example.FrameUpServer.Model.Visitor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Integer> {
}
