package com.kaplan.Assignment.service;

import com.kaplan.Assignment.model.Assignment;
import com.kaplan.Assignment.repository.AssignmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class AssignmentService {
    @Autowired
    AssignmentRepo assignmentRepo;


    public List<Assignment> getAllAssignments() {
        List<Assignment> assignmentList = assignmentRepo.findAll();

        return assignmentList;
    }

    @Transactional(readOnly = true)
    public Optional<Assignment> getAssignmentByID(Long id ) {
        return assignmentRepo.findById(id);
    }


    public Assignment save(Assignment assignment) {
        Assignment newAssignment= assignmentRepo.save(assignment);
        return newAssignment;
    }

    public List<Assignment>  getAssignmentsByTag(String tag) {
        return assignmentRepo.retrieveByTag(tag);
    }
}
