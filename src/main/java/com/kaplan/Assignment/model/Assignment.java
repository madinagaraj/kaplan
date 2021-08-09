package com.kaplan.Assignment.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "Assignment")
@Component
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long assignmentId;
    public String assignmentName;
    public String assignmentTitle;
    public String assignmentDescription;
    public String assignmentType;
    public Integer assignmentDurationNum;
    public String assignmentDurationType;

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


}
