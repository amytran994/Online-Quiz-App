package com.project.QuizDemo;

import com.project.QuizDemo.dao.impl.AdminHibernateDAOImpl;
import com.project.QuizDemo.domain.McQuestion;
import com.project.QuizDemo.domain.Submission;
import com.project.QuizDemo.domain.User;
import com.project.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public  static void main(String[] args) {
        AdminHibernateDAOImpl admin = new AdminHibernateDAOImpl();

//        System.out.println(admin.getAllUsers().get(0).getFirstname());


//        List<Submission> subList = admin.getAllSubmissionsByUsername("a");
//        System.out.println(subList == null);
//        for (int i=0; i < subList.size(); ++i) {
//            Submission sub = subList.get(i);
////            System.out.println(sub.getQuiz().getQuestionList().get(0).getOptions().get(0).getOpContent());
//            System.out.println(sub.getStartTime());
//        }


//        List<Integer> list =  Arrays.asList(new Integer[]{1,2}); // returns a fixed-size list backed by the specified array.
//
//        List<Submission> subList = admin.getAllSubmissionsByCategoryAndUsername(list, "a");
//        System.out.println(subList.size());
//        for (int i=0; i < subList.size(); ++i) {
//            Submission sub = subList.get(i);
//            System.out.println(sub.getQuiz().getName());
//            System.out.println(sub.getScore());
//        }
//
////           changeUserStatus
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        User user = session.get(User.class, 1);
//        session.close();
//        admin.changeUserStatus(user);
//
////             add new question
//            admin.addNewQuestion(2, "new Java question", "opt1", "opt2", "opt3", "opt4", 4);
//
////             update
//            List<String> opList = new ArrayList<>();
//            opList.add("MySQL question 1 option 1");
//            opList.add("MySQL question 1 option 2");
//            opList.add("newOpt3");
//            opList.add("newOpt4");
//
//            admin.updateQuestion(1, 1,"MySQL question 1",opList, 1  );


    }
}
