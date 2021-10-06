package com.iit.cms.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Coursework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    private Lecturer lecturer;

    @NotNull
    private Date creationDate;

    @NotNull
    private String dueDateAndTime;

    @NotNull
    @Size(min = 3, max = 50)
    private String courseworkName;

    @NotNull
    @Size(min = 3, max = 2000)
    private String courseworkFile;

    public Coursework() {
    }

    public Coursework(Lecturer lecturer, Date creationDate, String dueDateAndTime, @Size(min = 3, max = 50) String courseworkName, @Size(min = 3, max = 2000) String courseworkFile) {
        this.lecturer = lecturer;
        this.creationDate = creationDate;
        this.dueDateAndTime = dueDateAndTime;
        this.courseworkName = courseworkName;
        this.courseworkFile = courseworkFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCourseworkName() {
        return courseworkName;
    }

    public void setCourseworkName(String courseworkName) {
        this.courseworkName = courseworkName;
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
