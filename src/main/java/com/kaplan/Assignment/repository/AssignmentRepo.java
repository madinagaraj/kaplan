package com.kaplan.Assignment.repository;

import com.kaplan.Assignment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment,Long> {

    @Query("SELECT asg FROM Assignment asg JOIN asg.tags t WHERE t = LOWER(:tag)")
    List<Assignment> retrieveByTag(@Param("tag") String tag);

}


