package com.iit.cms.controller;

import com.iit.cms.Role;
import com.iit.cms.model.Lecturer;
import com.iit.cms.model.Student;
import com.iit.cms.repository.UserRepository;
import com.iit.cms.request.AddUser;
import com.iit.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/member")
public class MembersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute(name = "memberTypes")
    public List<String> memberTypes() {

        return Stream.of(Role.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    /**
     * Redirects to display users page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String showMembersPage(Model model) {

        model.addAttribute("members", userService.getAllUsers());
        return "/member/list";
    }

    /**
     * Redirects to the user registration page.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String addMemberPage(Model model) {

        model.addAttribute("member", new AddUser());
        return "/member/add";
    }

    /**
     * Save a user(student/lecturer) to the DB.
     *
     * @param user
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String saveMember(@Valid AddUser user, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

//        if( bindingResult.hasErrors() ) {
//            return "/member/add";
//        }

        switch (user.getRole()) {
            case STUDENT: {

                Student student = new Student(user.getDisplayName(), user.getUsername(), user.getGender(), user.getPassword());
                student.setPassword(passwordEncoder.encode(user.getPassword()));
                student.setCreatedDate(new Date());
                student.setLastModifiedDate(user.getCreatedDate());
                student.setActive(1);

                userRepository.save(student);

                redirectAttributes.addFlashAttribute("successMsg", "Student Added Successfully: " + user.getDisplayName());
                break;
            }
            case LECTURER: {

                Lecturer lecturer = new Lecturer(user.getDisplayName(), user.getUsername(), user.getGender(), user.getPassword());
                lecturer.setPassword(passwordEncoder.encode(user.getPassword()));
                lecturer.setCreatedDate(new Date());
                lecturer.setLastModifiedDate(user.getCreatedDate());
                lecturer.setActive(1);

                userRepository.save(lecturer);

                redirectAttributes.addFlashAttribute("successMsg", "Lecturer Added Successfully: " + user.getDisplayName());
                break;
            }
        }
        return "redirect:/member/add";
    }

}

