package com.kaplan.Assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AssignmentController {

    @GetMapping("/")
    public String Welcome(){

        return "Use http://localhost:8087/swagger-ui/index.html for more details on end points ";

    }

}
