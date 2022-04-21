package com.project.QuizDemo.dao;

import com.project.QuizDemo.domain.McQuestion;
import com.project.QuizDemo.domain.User;

import java.util.List;


public interface QuizDAO {

    List<McQuestion> getQuestionList(String category);

    void submitQuiz(List<McQuestion> qList, int[] selectedList, int score, String startTime, String endTime,
                    User user );


}
