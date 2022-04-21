package com.project.QuizDemo.controller;

import com.project.QuizDemo.domain.*;
import com.project.QuizDemo.service.impl.AdminService;
import com.project.QuizDemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Component
@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String mainPage(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "Please log in first");
            return "login";
        }
        if (user.isAdmin()) {
            return "admin_main";
        } else {
            model.addAttribute("message", "You don't have access to Admin page");
            return "homepage";
        }
    }

//    @GetMapping("/allsubmissions")
//    public ModelAndView getAllQuizzes(HttpServletRequest request, ModelMap model) {
//        List<Integer> categoryIdList = new ArrayList<>();
//        String usernameFilter = "";
//        List<Submission> submissionList = adminService.showAllQuizzes(categoryIdList, usernameFilter);
//
//        List<SubmissionResult> resultList = adminService.getSubmissionResultToDisplay();
//        model.addAttribute("resultList", resultList);
//        String page = "redirect";
//        return "admin_show_all_quizz_result";
//    }

    @GetMapping("/allsubmissions")
    public String showAllQuizzes(HttpServletRequest request, ModelMap model) {

            // if box is checked, button = category id
            // if box is not checked, button = 0
            // using hidden field to set unchecked button value

        List<Integer> categoryIdList = new ArrayList<>();

        if (request.getParameter("mySQLButton") != null) {
            Integer cate1 = Integer.parseInt(request.getParameter("mySQLButton"));
            if (cate1 != 0) {
                categoryIdList.add(cate1);
                }
        }

        if (request.getParameter("javaButton") != null) {
            Integer cate2 = Integer.parseInt(request.getParameter("javaButton"));
            if (cate2 != 0) {
                categoryIdList.add(cate2);
            }
        }

        if (request.getParameter("pythonButton") != null) {
            Integer cate3 = Integer.parseInt(request.getParameter("pythonButton"));
            if (cate3 != 0) {
                categoryIdList.add(cate3);
            }
        }

            String usernameFilter = "";
            if (request.getParameter("usernameFilter") != null) {
                usernameFilter = request.getParameter("usernameFilter");
            }

            List<Submission> submissionList = adminService.showAllQuizzes(categoryIdList, usernameFilter);

            List<SubmissionResult> resultList = adminService.getSubmissionResultToDisplay();
            model.addAttribute("resultList", resultList);
            return "admin_show_all_quizz_result";
    }

    @GetMapping("/allsubmissions/{id}")
    public String showQuizzDetail (@PathVariable int id, ModelMap model) {

        Submission sub = adminService.getSubmissionById(id);
        User user = sub.getUser();
        String userFullName = user.getFirstname() + " " + user.getLastname();
        String category = sub.getCategory().getName();

        model.addAttribute("startTime", sub.getStartTime());
        model.addAttribute("endTime", sub.getEndTime());
        model.addAttribute("name", userFullName);
        model.addAttribute("score", sub.getScore());
        model.addAttribute("category", category);


        List<QuizResult> resultList = adminService.getQuizResultToDisplay(id);
        model.addAttribute("resultList", resultList);
        return "resultdetail";
    }

    @GetMapping("/userlist/{id}")
    public String showUserDetaul (@PathVariable int id, ModelMap model) {
        User user = adminService.getUserbyId(id);
        model.addAttribute("user", user);
        return "admin_user_profile";
    }

    @GetMapping("/userlist")
    public String showUserList (ModelMap model) {
        List<User> userList = adminService.getAllUsers();
        model.addAttribute("userList", userList);
        return "admin_userlist";
    }

    @PostMapping("/userlist/changestatus/{id}")
    public ModelAndView changeStatus (@PathVariable int id, ModelMap model) {
        User user = adminService.getUserbyId(id);
        adminService.changeUserStatus(user);
        return new ModelAndView("redirect:/admin/userlist", model);
    }

    @GetMapping("/update")
    public String showUpdatePage(ModelMap model) {
        return "admin_update";
    }

    @PostMapping("/update/add")
    public String addQuestion(@RequestParam("content") String q,
    @RequestParam("category") int cate, @RequestParam("option1") String op1, @RequestParam("option2") String op2,
    @RequestParam("option3") String op3, @RequestParam("option4") String op4, @RequestParam("correct") int correct,
    ModelMap model) {
        adminService.addNewQuestion(cate, q, op1, op2, op3, op4, correct);
        model.addAttribute("message","Question Added");
        return "admin_update";
    }

    @PostMapping("/update/update")
    public String updateQuestion(HttpServletRequest request, @RequestParam("id") int id, ModelMap model) {
        String q = "";
        if (request.getParameter("content")!=null) {
            q = request.getParameter("content");
        }

        List<String> opList = new ArrayList<>();

        if (request.getParameter("option1")!=null) {
            opList.add(request.getParameter("option1"));
        } else {
            opList.add("");
        }

        if (request.getParameter("option2")!=null) {
            opList.add(request.getParameter("option2"));
        } else {
            opList.add("");
        }

        if (request.getParameter("option3")!=null) {
            opList.add(request.getParameter("option3"));
        } else {
            opList.add("");
        }

        if (request.getParameter("option4")!=null) {
            opList.add(request.getParameter("option4"));
        } else {
            opList.add("");
        }

        int cate = 0;
        if(request.getParameter("category")!=null) {
            cate = Integer.parseInt(request.getParameter("category"));
        }

        int correct = 0;
        if(request.getParameter("correct")!=null) {
            correct = Integer.parseInt(request.getParameter("correct"));
        }

        adminService.updateQuestion(cate, id, q, opList, correct);
        model.addAttribute("message","Question Updated");
        return "admin_update";
    }

    @PostMapping("/update/disable")
    public String delete(@RequestParam("id") int id, ModelMap model) {
        adminService.disableQuestion(id);
        model.addAttribute("message","Question Disabled");
        return "admin_update";
    }
}
