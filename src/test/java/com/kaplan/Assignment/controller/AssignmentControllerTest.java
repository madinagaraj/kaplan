package com.kaplan.Assignment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kaplan.Assignment.model.Assignment;
import com.kaplan.Assignment.repository.AssignmentRepo;
import com.kaplan.Assignment.service.AssignmentService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;

@WebMvcTest

public class AssignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentService assignmentService;



    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllAssignmentsTest() throws Exception {
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        Assignment assignment2 = returnCustomAssignment((long) 2,"Test2", "TestTitle2","TestDesc2","TestType2",2,"TestDurationType2");
        List<Assignment> response = new ArrayList<Assignment>();
        response.add(assignment1);
        response.add(assignment2);
        Mockito.when(assignmentService.getAllAssignments()).thenReturn(response);
        //when(assignmentRepo.findAll()).thenReturn(response);
        mockMvc.perform(get("/assignment/assignments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void getAllAssignmentsTestNull() throws Exception {
        Mockito.when(assignmentService.getAllAssignments()).thenReturn(null);
        mockMvc.perform(get("/assignment/assignments"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void createNewAssignmentTest() throws Exception {
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        Mockito.when(assignmentService.save(ArgumentMatchers.any())).thenReturn(assignment1);
        String json = mapper.writeValueAsString(assignment1);
        mockMvc.perform(post("/assignment/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewAssignmentTestValidationError() throws Exception {
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "","TestDesc1","TestType1",1,"TestDurationType1");
        Mockito.when(assignmentService.save(ArgumentMatchers.any())).thenReturn(assignment1);
        String json = mapper.writeValueAsString(assignment1);
        mockMvc.perform(post("/assignment/assignments")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void getAssignmentById() throws Exception {
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        Mockito.when(assignmentService.getAssignmentByID(ArgumentMatchers.any())).thenReturn(java.util.Optional.ofNullable(assignment1));
        String json = mapper.writeValueAsString(assignment1);
        mockMvc.perform(get("/assignment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAssignmentByIdNull() throws Exception {
        Mockito.when(assignmentService.getAssignmentByID((long)1)).thenReturn(null);
        mockMvc.perform(get("/assignment/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAssignmentByTag() throws Exception {
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        List<String> responseTags = Arrays.asList("Check 1");
        Assignment reponseByTag=setResponseTags(assignment1,responseTags);
        Mockito.when(assignmentService.getAssignmentsByTag(ArgumentMatchers.any())).thenReturn(Arrays.asList(reponseByTag));
        String json = mapper.writeValueAsString(assignment1);
        mockMvc.perform(get("/assignment/searchbytag/test")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAssignmentByTagNullValue() throws Exception {
        Mockito.when(assignmentService.getAssignmentsByTag(ArgumentMatchers.any())).thenReturn(null);
        mockMvc.perform(get("/assignment/searchbytag/test"))
                .andExpect(status().isNotFound());
    }

    public Assignment returnCustomAssignment(Long Id, String name, String title, String desc, String type, Integer durationNum, String durationType){

        Assignment assignment=new Assignment();
        assignment.setAssignmentId(Id);
        assignment.setAssignmentName(name);
        assignment.setAssignmentTitle(title);
        assignment.setAssignmentDescription(desc);
        assignment.setAssignmentType(type);
        assignment.setAssignmentDurationNum(durationNum);
        assignment.setAssignmentDurationType(durationType);

        return assignment;
    }

    public Assignment setResponseTags(Assignment assignment, List<String> tags){
        assignment.setTags(tags);
        return assignment;
    }

}

