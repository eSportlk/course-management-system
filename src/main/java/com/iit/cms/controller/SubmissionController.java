package com.iit.cms.controller;

import com.iit.cms.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    /**
     * Redirects to display submissions page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('LECTURER')")
    public String showSubmissionsPage(Model model) {

        model.addAttribute("submissions", submissionService.getAll());
        return "/submission/list";
    }

}
