package com.iit.cms.request;

import com.iit.cms.model.Lecturer;
import com.sun.istack.NotNull;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class AddCoursework {
    @NotNull
    @ManyToOne
    private Lecturer lecturer;

    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @NotNull
    private String creationDate;

    @NotNull
    private String dueDateAndTime;

    @NotNull
    @Size(min = 3, max = 2000)
    private String courseworkFile;

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDateAndTime() {
        return dueDateAndTime;
    }

    public void setDueDateAndTime(String dueDateAndTime) {
        this.dueDateAndTime = dueDateAndTime;
    }

    public String getCourseworkFile() {
        return courseworkFile;
    }

    public void setCourseworkFile(String courseworkFile) {
        this.courseworkFile = courseworkFile;
    }
}
