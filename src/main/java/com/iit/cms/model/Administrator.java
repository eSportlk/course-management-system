package com.iit.cms.model;

import javax.persistence.Entity;

@Entity
public class Administrator extends User {

    public Administrator() {

    }

    public Administrator(String displayName, String username, String gender, String password) {

        super(displayName, username, gender, password);
    }
}
