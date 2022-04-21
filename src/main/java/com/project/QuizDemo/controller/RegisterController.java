package com.project.QuizDemo.controller;

import com.project.QuizDemo.service.impl.UserService;
import com.project.QuizDemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    @Autowired
    public void setEmployeeService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String register(Model model) {
        return "register";
    }

    @PostMapping
    public String register (@ModelAttribute User user, @RequestParam(value="isAdmin", required=false) String admin,
    @RequestParam("password2") String password2, Model model) {


        String username = user.getUsername();
        String password = user.getPassword();
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String email = user.getEmail();
        String address = user.getAddress();
        String phoneNumber = user.getPhoneNumber();

        boolean isAdmin = false;
        if (admin!= null && admin.equals("true")) {
            isAdmin = true;
        }

        if (password.equals(password2) == false ) {
            String msg = "Password confirm is not matched";
            model.addAttribute("message", msg);
            return "errorpage";
        }

        int result = this.userService.register(username, password, firstname, lastname, email, address, phoneNumber, isAdmin);

        if (result == 0) {
            String msg = "Can not register. Please try again.";
            model.addAttribute("message", msg);
            model.addAttribute("page", "register");
            return "errorpage";
        }

        return "login";
    }

}
