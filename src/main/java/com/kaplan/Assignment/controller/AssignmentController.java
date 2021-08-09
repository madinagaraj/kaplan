package com.kaplan.Assignment.controller;

import com.kaplan.Assignment.model.Assignment;
import com.kaplan.Assignment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Assignment> getAllAssignments(){
        return assignmentService.getAllAssignments();
    }

    @PostMapping("/assignments")
    String createNewAssignment(@RequestBody Assignment newAssignment) {
        Assignment result= assignmentService.save(newAssignment);
        return "Assignment created with ID: ".concat(result.getAssignmentId().toString());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Assignment getAssignmentById(@PathVariable Long id) {
        Assignment assignmentResponse = (Assignment) assignmentService.getAssignmentByID(id);
        return assignmentResponse;
    }

}
