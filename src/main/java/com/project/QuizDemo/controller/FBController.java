package com.project.QuizDemo.controller;

import com.project.QuizDemo.service.impl.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class FBController {

    private FBService fbService;

    @Autowired
    public void setFbService(FBService fbService) {
        this.fbService = fbService;
    }

    @GetMapping ("feedback")
    public String getFeedbackForm(ModelMap model) {
       return "fb";
    }

    @PostMapping("feedback")
    public String submitFeedback(@RequestParam("feedback") String text, @RequestParam("rating") int rating,
     @RequestParam(required = false, value="id") Integer id, ModelMap model) {

        if (id != null) {
            model.addAttribute("id", id);
        }

        fbService.addFeedback(text, rating);
        model.addAttribute("message", "Thank you for your feedback!");
        return "fb";
    }
}
