package com.iit.cms.model;

import javax.persistence.Entity;

@Entity
public class Lecturer extends User {

    public Lecturer() {

    }

    public Lecturer(String displayName, String username, String gender, String password) {

        super(displayName, username, gender, password);
    }
}
