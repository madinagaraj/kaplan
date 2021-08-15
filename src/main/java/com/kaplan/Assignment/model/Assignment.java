package com.kaplan.Assignment.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Assignment")
@SequenceGenerator(name = "assignment_no_gen", sequenceName = "assignment_no_gen",  initialValue = 5000)
@Component
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "assignment_no_gen")
    public Long assignmentId;
    
    @NotEmpty(message="Assignment Name not found")
    public String assignmentName;
    @NotEmpty(message="Assignment Title not found")
    public String assignmentTitle;
    public String assignmentDescription;
    @NotEmpty(message="Assignment Type not found")
    public String assignmentType;
    @Min(1)
    public Integer assignmentDurationNum;
    @NotEmpty(message="Assignment Duration Type not found")
    public String assignmentDurationType;

    @ElementCollection(targetClass=String.class)
    @CollectionTable(name ="tags")
    public List<String> tags = new ArrayList<String>();


    public Long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public Integer getAssignmentDurationNum() {
        return assignmentDurationNum;
    }

    public void setAssignmentDurationNum(Integer assignmentDurationNum) {
        this.assignmentDurationNum = assignmentDurationNum;
    }

    public String getAssignmentDurationType() {
        return assignmentDurationType;
    }

    public void setAssignmentDurationType(String assignmentDurationType) {
        this.assignmentDurationType = assignmentDurationType;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
