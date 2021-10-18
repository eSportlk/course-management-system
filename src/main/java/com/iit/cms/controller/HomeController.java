package com.iit.cms.controller;

import com.iit.cms.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage(Model model) {

        model.addAttribute("topTiles", homeService.getData());
        return "home";
    }

}

