package com.iit.cms;

import com.iit.cms.service.UserService;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AnnotationAdvice {

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {

        return UserService.getRole() == Role.ADMIN;
    }

    @ModelAttribute("isLecturer")
    public boolean isLecturer() {

        return UserService.getRole() == Role.LECTURER;
    }
}