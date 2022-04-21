package com.project.QuizDemo.controller;


import com.project.QuizDemo.dao.ContactDAO;
import com.project.QuizDemo.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contactus")
public class ContactController {

    private ContactDAO contactJdbcDao;

    @Autowired
    public void setContactJdbcDao(ContactDAO contactJdbcDao) {
        this.contactJdbcDao = contactJdbcDao;
    }

    @GetMapping
    public String showContact(Model model) {

        List<Contact> contactList = contactJdbcDao.getAllContact();
        model.addAttribute("contactList", contactList);

        return "contactus";
    }
}

