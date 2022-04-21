package com.project.QuizDemo.controller;

import com.project.QuizDemo.domain.User;
import com.project.QuizDemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "message", required = false) String message) {

        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login (HttpServletRequest request, @RequestParam("username") String username,
    @RequestParam("password") String password, ModelMap model) {

        User user = this.userService.login(username, password);

        if (user == null) {
            model.addAttribute("message", "Cannot log in. Try again or register!");

            String page = "redirect:/login";
            return new ModelAndView("login", model);
        }
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        // generate new session
        HttpSession session = request.getSession();

        // store username in session
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("user", user);
        session.setAttribute("isAdmin", user.isAdmin());

        model.addAttribute("message", "Welcome " + user.getFirstname());

        String page = "redirect:/homepage";
        return new ModelAndView(page, model);

    }

    @GetMapping("logout")
    public String logout (HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        session.invalidate();
        return "login";
    }


}
