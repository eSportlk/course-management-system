package com.iit.cms.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {

    public Student() {

    }

    public Student(String displayName, String username, String gender, String password) {

        super(displayName, username, gender, password);
    }

}
