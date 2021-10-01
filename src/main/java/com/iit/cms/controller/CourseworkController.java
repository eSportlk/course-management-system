package com.iit.cms.controller;

import com.iit.cms.model.*;
import com.iit.cms.repository.CourseworkRepository;
import com.iit.cms.repository.SubmissionRepository;
import com.iit.cms.repository.UserRepository;
import com.iit.cms.request.AddCoursework;
import com.iit.cms.service.CourseworkService;
import com.iit.cms.service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/coursework")
public class CourseworkController {

    @Autowired
    CourseworkRepository courseworkRepository;

    @Autowired
    CourseworkService courseworkService;

    @Autowired
    SubmissionRepository submissionRepository;

    @Autowired
    UserRepository userRepository;

    public User getUser() {
        Long uid = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return userRepository.findById(uid).orElse(null);
    }

    /**
     * Redirects to add coursework page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('LECTURER','ADMINISTRATOR')")
    public String showAddCourseworkPage(Model model) {

        model.addAttribute("coursework", new AddCoursework());
        return "/coursework/add";
    }

    /**
     * Save the coursework details.
     *
     * @param addCoursework
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAnyAuthority('LECTURER','ADMINISTRATOR')")
    public String saveCoursework(@Valid AddCoursework addCoursework, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        User user = getUser();
        if (user instanceof Lecturer) {
            Coursework coursework = new Coursework();
            coursework.setLecturer((Lecturer) user);
            coursework.setCourseworkName(addCoursework.getName());
            coursework.setCourseworkFile(addCoursework.getCourseworkFile());
            coursework.setDueDateAndTime(addCoursework.getDueDateAndTime());
            coursework.setCreationDate(new Date());

            courseworkRepository.save(coursework);

            redirectAttributes.addFlashAttribute("successMsg", "Coursework Added Successfully: " + coursework.getCourseworkName());
        }

        return "redirect:/coursework/add";
    }

    /**
     * Redirects to display coursework page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/view"}, method = RequestMethod.GET)
    public String showViewCourseworkPage(Model model) {

        model.addAttribute("coursework", courseworkService.getAll());
        return "/coursework/view";
    }

    /**
     * Redirects to submit coursework page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/submit"}, method = RequestMethod.GET)
    public String showSubmitCourseworkPage(@RequestParam("id") String courseworkID, Model model) {
        model.addAttribute("submission", new Submission());
        model.addAttribute("coursework", courseworkID);
        return "/coursework/submit";
    }

    @RequestMapping(value = "/submit/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('STUDENT')")
    public String addSubmission(@Valid com.iit.cms.request.Submission submission, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

        User user = getUser();
        if (user instanceof Student) {
            Submission submission1 = new Submission();
            Coursework coursework = courseworkRepository.findById(Long.valueOf(submission.getCoursework())).orElse(null);

            if (coursework != null) {
                submission1.setCoursework(coursework);
                submission1.setCourseworkFile(submission.getCourseworkFile());
                submission1.setStudent((Student) user);
                submission1.setCreationDate(new Date());

                submissionRepository.save(submission1);
            }

            redirectAttributes.addFlashAttribute("successMsg", "Submission Successful");
        }

        return "redirect:/coursework/view";
    }
}
