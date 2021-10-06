package com.iit.cms.request;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class Submission {

    @NotNull
    @Size(min = 1, max = 100)
    private String coursework;

    @NotNull
    @Size(min = 3, max = 2000)
    private String courseworkFile;

    public String getCoursework() {
        return coursework;
    }

    public void setCoursework(String coursework) {
        this.coursework = coursework;
    }

    public String getCourseworkFile() {
        return courseworkFile;
    }

    public void setCourseworkFile(String courseworkFile) {
        this.courseworkFile = courseworkFile;
    }
}
