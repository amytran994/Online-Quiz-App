package com.project.QuizDemo.service.impl;

import com.project.QuizDemo.dao.AdminDAO;
import com.project.QuizDemo.dao.UserDAO;
import com.project.QuizDemo.dao.impl.AdminHibernateDAOImpl;
import com.project.QuizDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminService {

    private AdminDAO adminHibernateDao;
    private List<Submission> submissionList;
    private List<User> userList;

    @Autowired
    public void setAdminHibernateDao(AdminDAO adminHibernateDao) {
        this.adminHibernateDao = adminHibernateDao;
    }

    public List<Submission> showAllQuizzes(List<Integer> categoryIdList, String username) {

        if (categoryIdList.size() == 0 && username.length() == 0) {

            this.submissionList = adminHibernateDao.getAllSubmissions();
        }
        else if (categoryIdList.size() == 0 && username.length() != 0) {

            this.submissionList = adminHibernateDao.getAllSubmissionsByUsername(username);
        }
        else if (categoryIdList.size() != 0 && username.length() == 0) {

            this.submissionList = adminHibernateDao.getAllSubmissionsByCategory(categoryIdList);
        }
        else {
            this.submissionList = adminHibernateDao.getAllSubmissionsByCategoryAndUsername(categoryIdList, username);
        }
        return this.submissionList;
    }


    public List<User> getAllUsers() {
        this.userList = adminHibernateDao.getAllUsers();
        return this.userList;
    }

    public void changeUserStatus(User user) {
        adminHibernateDao.changeUserStatus(user);
    }

    public User getUserbyId(int id) {
        return adminHibernateDao.getUserbyId(id);
    }

    public void addNewQuestion(int categoryId, String q, String opt1, String opt2, String opt3, String opt4, int correctOption) {
        adminHibernateDao.addNewQuestion(categoryId, q, opt1, opt2, opt3, opt4, correctOption);
    }

    public void updateQuestion(int categoryId, int questionId, String qContent, List<String> optContent, int isCorrect) {
        adminHibernateDao.updateQuestion(categoryId, questionId, qContent, optContent, isCorrect);
    }

    public void disableQuestion(int id) {
        adminHibernateDao.disableQuestion(id);
    }

    public List<SubmissionResult> getSubmissionResultToDisplay() {
        List<SubmissionResult> resList = new ArrayList<>();
        if (this.submissionList.size()==0) {
            return null;
        }
        Submission sub;
        User user;
        String userFullname, cateName, date;
        SubmissionResult res;
        for (int i = 0; i < this.submissionList.size(); ++i) {

            sub = this.submissionList.get(i);
            cateName = sub.getCategory().getName();
            date = sub.getStartTime();

            user = sub.getUser();
            userFullname = user.getFirstname() + " " + user.getLastname();

            res = new SubmissionResult().builder().categoryName(cateName).date(date)
            .qNum(10).score(sub.getScore()).userFullname(userFullname).id(sub.getId()).build();

            resList.add(res);
        }
        return resList;
    }
    public List<QuizResult> getQuizResultToDisplay(int id) {

        List<QuizResult> resultList = new ArrayList<>();
        Submission sub = adminHibernateDao.getSubmissionById(id);
        int quizId = sub.getQuiz().getId();
        List<McOption> selectedList = sub.getQuiz().getSelectectList();
        McQuiz quiz = adminHibernateDao.getQuizById(quizId);
        List<McQuestion> qList = quiz.getQuestionList();

        for (int i = 0; i < qList.size(); ++i) {

            int qId = qList.get(i).getId();
            McQuestion q = adminHibernateDao.getQuestionById(qId);

            McOption opt1 = q.getOptions().get(0);
            McOption opt2 = q.getOptions().get(1);
            McOption opt3 = q.getOptions().get(2);
            McOption opt4 = q.getOptions().get(3);

            String op1 = opt1.getOpContent();
            String op2 = opt2.getOpContent();
            String op3 = opt3.getOpContent();
            String op4 = opt4.getOpContent();

            int correctOpt = 0;
            for (int j = 0; j < 4; ++j) {
                McOption op = q.getOptions().get(j);
                if (op.isCorrect()) {
                    correctOpt = j+1;
                }
            }

            int selected = 0;

            if (selectedList.size() > 0) {
                int selectedId = selectedList.get(i).getId();
                if (selectedId == opt1.getId()) {
                    selected = 1;

                } else if (selectedId == opt2.getId()) {
                    selected = 2;

                } else if (selectedId == opt3.getId()) {
                    selected = 3;

                } else if (selectedId == opt4.getId()) {
                    selected = 4;

                }

            }

            boolean correct = false;

            if (correctOpt == selected) {
                correct = true;
            }

            QuizResult res = new QuizResult().builder().qnum(i+1).question(q.getContent()).opt1(op1).opt2(op2).opt3(op3).opt4(op4).checked(selected).correctOpt(correctOpt).correct(correct).build();

            resultList.add(res);
        }
        return resultList;
    }

    public Submission getSubmissionById(int id) {
        return adminHibernateDao.getSubmissionById(id);
    }
}
