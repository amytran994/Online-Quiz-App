package com.project.QuizDemo.controller;

import com.project.QuizDemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomepageController {

    private UserService userService;

    @Autowired
    public void setEmployeeService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("homepage")
    public String getHomepage(HttpServletRequest request,
    @RequestParam(value="message", required=false) String msg, ModelMap model) {

        String username = (String) request.getSession().getAttribute("username");

        if (username != null) {
            model.addAttribute("message", msg);
            return "homepage";
        } else {
            model.addAttribute("message", "Please log in first");
            return "login";
        }
    }

}
