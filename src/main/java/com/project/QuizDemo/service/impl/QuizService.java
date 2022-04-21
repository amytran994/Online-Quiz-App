package com.project.QuizDemo.service.impl;

import com.project.QuizDemo.dao.QuizDAO;
import com.project.QuizDemo.dao.UserDAO;
import com.project.QuizDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuizService {

    private QuizDAO quizJdbcDao;
    private UserDAO userJdbcDao;

    private List<McQuestion> qList;
    private List<QuizResult> resultList;
    private int[] selectedArray;
    private int score;
    private String startTime;
    private String endTime;
    private User user;


    @Autowired
    public void setQuizJdbcDao(QuizDAO quizJdbcDao) {
        this.quizJdbcDao = quizJdbcDao;
    }

    @Autowired
    public void setUserJdbcDao(UserDAO userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }

    public void selectOption(int questionNum, int selected) {
        this.selectedArray[questionNum-1] = selected;
    }

    public List<McQuestion> getQuestionList() {

        return this.qList;
    }

    public void setQuestionList(String category) {
        this.qList = quizJdbcDao.getQuestionList(category);
    }

    public void startQuiz(String category, User user) {
        // reset all the fields when starting a new quiz
        setQuestionList(category);
        this.selectedArray = new int[]{ 0,0,0,0,0,0,0,0,0,0 };
        setStartTime(LocalDateTime.now().toString());
        this.user = user;
        setScore(0);
    }

    public McQuestion getQuestion(int qnum) {
        return this.qList.get(qnum-1);
    }

    public int getslected(int qnum) {
        return this.selectedArray[qnum-1];
    }

    public void submitQuiz() {
        // Check if all question has selected option

        setScore(caculateScore());

        // return?
        quizJdbcDao.submitQuiz(getQuestionList(), getSelectedArray(),
        getScore(), getStartTime(), getEndTime(), this.user);

        List<QuizResult> resultList = new ArrayList<>();

        for (int i = 0; i < this.qList.size(); ++i) {

            McQuestion q = this.qList.get(i);

            String op1 = q.getOptions().get(0).getOpContent();
            String op2 = q.getOptions().get(1).getOpContent();
            String op3 = q.getOptions().get(2).getOpContent();
            String op4 = q.getOptions().get(3).getOpContent();

            int selected = this.selectedArray[i];

            boolean correct = false;
            if (q.getCorrect() == selected) {
                correct = true;
            }

            QuizResult res = new QuizResult().builder().qnum(i+1).question(q.getContent()).opt1(op1).opt2(op2).opt3(op3).opt4(op4).checked(selected).correctOpt(q.getCorrect()).correct(correct).build();

            resultList.add(res);
        }

        this.resultList = resultList;
    }

    public int caculateScore() {
        int score = 0;
        int correct;
        McQuestion q;

        for (int i=0; i < 10; ++i) {
            if (this.selectedArray[i] != 0) {

                q = qList.get(i);
                correct = q.getCorrect();

                if (correct == this.selectedArray[i]) {
                    score++;
                }
            }
        }
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public int[] getSelectedArray() {
        return this.selectedArray;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public String getUsername() {
        String name = user.getFirstname() + " " + user.getLastname();
        return name;
    }
    public List<QuizResult> getResultList() {
        return this.resultList;
    }
}
