package com.project.QuizDemo.dao;

import com.project.QuizDemo.domain.McQuestion;
import com.project.QuizDemo.domain.McQuiz;
import com.project.QuizDemo.domain.Submission;
import com.project.QuizDemo.domain.User;

import java.util.List;

public interface AdminDAO {

    List<User> getAllUsers();
    List<Submission> getAllSubmissionsByCategoryAndUsername(List<Integer> categoryIdList, String username);
    List<Submission> getAllSubmissionsByCategory(List<Integer> categoryIdList);
    List<Submission> getAllSubmissionsByUsername(String username);
    List<Submission> getAllSubmissions();
    void addNewQuestion(int categoryId, String q, String opt1, String opt2, String opt3, String opt4, int correctOption);
    void changeUserStatus(User user);
    void updateQuestion(int categoryId, int questionId, String qContent, List<String> optContent, int isCorrect);

    void disableQuestion(int id);

    User getUserbyId(int id);
    Submission getSubmissionById(int id);
    McQuiz getQuizById(int id);
    McQuestion getQuestionById(int id);

}
