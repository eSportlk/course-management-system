package com.iit.cms.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    private Student student;

    @NotNull
    private Date creationDate;

    @NotNull
    @ManyToOne
    private Coursework coursework;

    @NotNull
    @Size(min = 3, max = 2000)
    private String courseworkFile;

    public Submission() {
    }

    public Submission(Student student, Date creationDate, Coursework coursework, @Size(min = 3, max = 2000) String courseworkFile) {
        this.student = student;
        this.creationDate = creationDate;
        this.coursework = coursework;
        this.courseworkFile = courseworkFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student lecturer) {
        this.student = lecturer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Coursework getCoursework() {
        return coursework;
    }

    public void setCoursework(Coursework courseworkName) {
        this.coursework = courseworkName;
    }

    public String getCourseworkFile() {
        return courseworkFile;
    }

    public void setCourseworkFile(String courseworkFile) {
        this.courseworkFile = courseworkFile;
    }
}
