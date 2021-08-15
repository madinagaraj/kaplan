package com.kaplan.Assignment.controller;

import com.kaplan.Assignment.exception.AssignmentNotFoundException;
import com.kaplan.Assignment.exception.NoAssignmentWithTagFoundException;
import com.kaplan.Assignment.model.Assignment;
import com.kaplan.Assignment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/assignment")
public class AssignmentController {
    @Autowired
    AssignmentService assignmentService;

    @GetMapping("/")
    public String Welcome(){
        return "Use http://localhost:8087/swagger-ui/index.html for more details on end points ";
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<Assignment>> getAllAssignments(){
        List<Assignment> allAssignments = new ArrayList<Assignment>();
        allAssignments=assignmentService.getAllAssignments();
        if (allAssignments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(allAssignments, HttpStatus.OK);
    }


    @PostMapping("/assignments")
    ResponseEntity<String> createNewAssignment(@Valid @RequestBody Assignment newAssignment) {
        Assignment result= assignmentService.save(newAssignment);
        return ResponseEntity.ok("Assignment created with ID: ".concat(result.getAssignmentId().toString()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Optional<Assignment> assignmentByID = assignmentService.getAssignmentByID(id);
        if (ObjectUtils.isEmpty(assignmentByID)){
            throw new AssignmentNotFoundException("Not found Tutorial with id = " + id);
        } else
        return new ResponseEntity<>(assignmentByID.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/searchbytag/{tag}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Assignment>> getAssignmentBytag(@PathVariable String tag) {
        List<Assignment> assignmentsTagList = assignmentService.getAssignmentsByTag(tag);
        if (ObjectUtils.isEmpty(assignmentsTagList)){
            throw new NoAssignmentWithTagFoundException("Not found Tutorial with Tag = " + tag);
        } else
            return new ResponseEntity<>(assignmentsTagList, HttpStatus.OK);

    }

}
