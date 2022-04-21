package com.project.QuizDemo.controller;

import com.project.QuizDemo.domain.*;
import com.project.QuizDemo.service.impl.QuizService;
import com.project.QuizDemo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    private QuizService quizService;

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{category}")
    public ModelAndView getQuiz (HttpServletRequest request,
    @PathVariable String category, ModelMap model) {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if (user != null) {
            this.quizService.startQuiz(category, user);
            String page = "redirect:/quiz/{category}/1";
            return new ModelAndView(page, model);
        }
        return new ModelAndView("login", model);
    }

    @GetMapping("/{category}/{qnum}")
    public String getQuestion (@PathVariable String category, @PathVariable int qnum, ModelMap model) {

        McQuestion q = quizService.getQuestion(qnum);

        model.addAttribute("category", category);
        model.addAttribute("content",q.getContent());

        List<McOption> optList = q.getOptions();

        model.addAttribute("option1", optList.get(0).getOpContent());
        model.addAttribute("option2", optList.get(1).getOpContent());
        model.addAttribute("option3", optList.get(2).getOpContent());
        model.addAttribute("option4", optList.get(3).getOpContent());

        int select = quizService.getslected(qnum);

        if (select ==1 ) {
            model.addAttribute("check1", true);
            model.addAttribute("check2", false);
            model.addAttribute("check3", false);
            model.addAttribute("check4", false);

        } else if (select ==2 ) {
            model.addAttribute("check1", false);
            model.addAttribute("check2", true);
            model.addAttribute("check3", false);
            model.addAttribute("check4", false);

        } else if (select ==3 ) {
            model.addAttribute("check1", false);
            model.addAttribute("check2", false);
            model.addAttribute("check3", true);
            model.addAttribute("check4", false);

        } else if (select ==4 ) {
            model.addAttribute("check1", false);
            model.addAttribute("check2", false);
            model.addAttribute("check3", false);
            model.addAttribute("check4", true);
        }

        return "quiz";
    }

    @PostMapping("/{category}/{qnum}")
    public ModelAndView nextQuestion (HttpServletRequest request,
                                      @PathVariable String category, @PathVariable int qnum,
                                      @RequestParam(value="radioName", required=false) Integer selected,
                                      @RequestParam("button") String button, ModelMap model) {
        if (selected != null) {
            quizService.selectOption(qnum, selected);
        }

        if (button.equals("Submit")) {
            quizService.setEndTime(LocalDateTime.now().toString());
            quizService.submitQuiz();
            model.addAttribute("category", category);

            return new ModelAndView("redirect:/quiz/result", model);
        }
        if (button.equals("Prev")) {
            model.addAttribute("qnum",qnum-1);
        } else if (button.equals("Next")) {
            model.addAttribute("qnum",qnum+1);
        }

        String page = "redirect:/quiz/{category}/{qnum}";
        return new ModelAndView(page, model);
    }

    @GetMapping("/result")
    public String showResult( ModelMap model) {
        int score = quizService.caculateScore();

        model.addAttribute("startTime", quizService.getStartTime());
        model.addAttribute("endTime", quizService.getEndTime());
        model.addAttribute("name", quizService.getUsername());
        model.addAttribute("score", quizService.getScore());
        model.addAttribute("resultList", quizService.getResultList());

        return "resultdetail";
    }

//    @GetMapping("/result/{qnum}")
//    public String getResult (@PathVariable String category, @PathVariable int qnum, ModelMap model) {
//
//        McQuestion q = quizService.getQuestion(qnum);
//
//        model.addAttribute("category", category);
//
//        model.addAttribute("content",q.getContent());
//
//        List<McOption> optList = q.getOptions();
//        model.addAttribute("option1", optList.get(0).getOpContent());
//        model.addAttribute("option2", optList.get(1).getOpContent());
//        model.addAttribute("option3", optList.get(2).getOpContent());
//        model.addAttribute("option4", optList.get(3).getOpContent());
//
//        model.addAttribute("startTime", quizService.getStartTime());
//        model.addAttribute("endTime", quizService.getEndTime());
//
//        model.addAttribute("name", quizService.getUsername());
//
//        int correct = q.getCorrect();
//        int select = quizService.getslected(qnum);
//
//
//        if (select==correct) {
//            model.addAttribute("correct", "Correct Answer!");
//        } else {
//            model.addAttribute("correct", "Incorrect Answer!");
//        }
//
//        int score = quizService.caculateScore();
//        model.addAttribute("score", score);
//
//        if (select ==1 ) {
//            model.addAttribute("check1", true);
//            model.addAttribute("check2", false);
//            model.addAttribute("check3", false);
//            model.addAttribute("check4", false);
//
//        } else if (select ==2 ) {
//            model.addAttribute("check1", false);
//            model.addAttribute("check2", true);
//            model.addAttribute("check3", false);
//            model.addAttribute("check4", false);
//
//        } else if (select ==3 ) {
//            model.addAttribute("check1", false);
//            model.addAttribute("check2", false);
//            model.addAttribute("check3", true);
//            model.addAttribute("check4", false);
//
//        } else if (select ==4 ) {
//            model.addAttribute("check1", false);
//            model.addAttribute("check2", false);
//            model.addAttribute("check3", false);
//            model.addAttribute("check4", true);
//        }
//
//        return "result";
//    }
//
//    @PostMapping("/{category}/result/{qnum}")
//    public ModelAndView nextResult (@PathVariable String category, @PathVariable int qnum,
//     @RequestParam(value="radioName", required=false) Integer selected,
//     @RequestParam(value="button", required=false) String button ,  ModelMap model) {
//
//
//        if (button.equals("Prev")) {
//            model.addAttribute("qnum",qnum-1);
//        } else if (button.equals("Next")) {
//            model.addAttribute("qnum",qnum+1);
//        }
//
//        String page = "redirect:/quiz/{category}/result/{qnum}";
//        return new ModelAndView(page, model);
//    }

}
