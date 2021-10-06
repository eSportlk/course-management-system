package com.iit.cms.request;

//import com.iit.cms.Role;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * sign up request object class.
 */
public class AddUser {

    @Null
    private int id;

    @NotBlank
    @Size(min = 3, max = 40)
    private String displayName;

    @NotBlank
    @Size(min = 1, max = 1)
    private String gender;

    @NotBlank
    @Size(max = 50)
    @Email
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotNull
    private Date createdDate;

    @NotNull
    private Role role;

    // getters and setters

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getDisplayName() {

        return displayName;
    }

    public void setDisplayName(String displayName) {

        this.displayName = displayName;
    }

    public String getGender() {

        return gender;
    }

    public void setGender(String gender) {

        this.gender = gender;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {

        this.createdDate = createdDate;
    }

    public Role getRole() {

        return role;
    }

    public void setRole(Role role) {

        this.role = role;
    }
}
