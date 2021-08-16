package com.kaplan.Assignment.service;

import com.kaplan.Assignment.model.Assignment;
import com.kaplan.Assignment.repository.AssignmentRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssignmentServiceTest {

    @Mock
    AssignmentRepo assignmentRepo;
    @InjectMocks
    AssignmentService testService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllEmployeesTest(){
        Assignment assignment1 = returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        Assignment assignment2 = returnCustomAssignment((long) 2,"Test2", "TestTitle2","TestDesc2","TestType2",2,"TestDurationType2");
        List<Assignment> response = new ArrayList<Assignment>();
        response.add(assignment1);
        response.add(assignment2);

        when(assignmentRepo.findAll()).thenReturn(response);

        List<Assignment> testResponse = testService.getAllAssignments();
        assertEquals(2, testResponse.size());
        verify(assignmentRepo, times(1)).findAll();
    }

    @Test
    public void getAssignmentByIdTest(){
        Assignment reponseById= returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        when(assignmentRepo.findById((long) 1)).thenReturn(Optional.ofNullable(reponseById));
        Optional<Assignment> assignmentById= testService.getAssignmentByID((long)1);
        assertEquals("Test1",assignmentById.get().getAssignmentName());
        assertEquals("TestTitle1",assignmentById.get().getAssignmentTitle());
        assertEquals("TestDesc1",assignmentById.get().getAssignmentDescription());
        assertEquals(1,assignmentById.get().getAssignmentDurationNum());
    }

    @Test
    public void getAssignmentByTagTest(){
        Assignment reponseByTag= returnCustomAssignment((long) 1,"Test1", "TestTitle1","TestDesc1","TestType1",1,"TestDurationType1");
        List<String> responseTags = Arrays.asList("Check 1");
        reponseByTag=setResponseTags(reponseByTag,responseTags);

        Assignment reponseByTag2= returnCustomAssignment((long) 2,"Test2", "TestTitle2","TestDesc2","TestType2",2,"TestDurationType2");
        List<String> responseTags2 = Arrays.asList("Check 1","Check 2");
        reponseByTag2=setResponseTags(reponseByTag2,responseTags2);

        List<Assignment> responseFinal = Arrays.asList(reponseByTag);
        when(assignmentRepo.retrieveByTag("Check 1")).thenReturn(responseFinal);

        List<Assignment> assignmentByTagResp= testService.getAssignmentsByTag("Check 1");
        assertEquals(1,assignmentByTagResp.size());

        List<Assignment> responseFinal2 = Arrays.asList(reponseByTag,reponseByTag2);
        when(assignmentRepo.retrieveByTag("Check 2")).thenReturn(responseFinal2);

        List<Assignment> assignmentByTagResp2= testService.getAssignmentsByTag("Check 2");
        assertEquals(2,assignmentByTagResp2.size());
    }

    public Assignment returnCustomAssignment(Long Id, String name, String title, String desc, String type, Integer durationNum, String durationType){

        Assignment assignment=new Assignment();
        assignment.setAssignmentId(Id);
        assignment.setAssignmentName(name);
        assignment.setAssignmentTitle(title);
        assignment.setAssignmentDescription(desc);
        assignment.setAssignmentDurationType(type);
        assignment.setAssignmentDurationNum(durationNum);
        assignment.setAssignmentDurationType(durationType);

        return assignment;
    }

    public Assignment setResponseTags(Assignment assignment, List<String> tags){
         assignment.setTags(tags);
        return assignment;
    }
}
