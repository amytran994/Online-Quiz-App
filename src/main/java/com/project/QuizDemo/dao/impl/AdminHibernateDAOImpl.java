package com.project.QuizDemo.dao.impl;

import com.project.QuizDemo.dao.AdminDAO;
import com.project.QuizDemo.domain.*;

import com.project.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AdminHibernateDAO")
public class AdminHibernateDAOImpl implements AdminDAO {

    @Override
    public List<User> getAllUsers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("from User");
        List<User> userList = query.list();
        tx.commit();
        return userList;
    }

    @Override
    public List<Submission> getAllSubmissionsByCategoryAndUsername(List<Integer> categoryIdList, String username){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        String q = "from Submission as s " +
                   "join fetch s.category as c " +
                   "join fetch s.user as user where c.id in (:cate_id_list) " +
                   " and user.username = :username order by s.startTime desc";

        Query query = session.createQuery(q);
        List<Submission> submissionList = query.setParameter("cate_id_list", categoryIdList)
                .setParameter("username", username).list();
                tx.commit();
        return submissionList;
    }

    @Override
    public List<Submission> getAllSubmissionsByCategory(List<Integer> categoryIdList){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("from Submission as s join fetch s.category as c " +
         "join fetch s.user as user " +
         "where c.id in (:cate_id_list) order by s.startTime desc");
        List<Submission> submissionList = query.setParameter("cate_id_list", categoryIdList).list();
        tx.commit();
        return submissionList;
    }

    @Override
    public List<Submission> getAllSubmissionsByUsername(String username){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        String q = "from Submission as s " +
                    "join fetch s.user as u " +
                     "join fetch s.category as c where u.username = :username order by s.startTime desc";
        Query query = session.createQuery(q).setParameter("username", username);
        List<Submission> submissionList = query.list();
        tx.commit();
        return submissionList;
    }

    @Override
    public List<Submission> getAllSubmissions() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("from Submission s join fetch s.user " +
          "join fetch s.category " +
           "order by startTime desc");
        List<Submission> submissionList = query.list();
        tx.commit();
        return submissionList;
    }


    @Override
    public void changeUserStatus(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            if (user.isActive() == true) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }

            session.update(user); // user update to assure that it will not create any new row
            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
    }


    @Override
    public void addNewQuestion(int categoryId, String q, String opt1, String opt2, String opt3, String opt4, int correctOption) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Category category = session.get(Category.class, categoryId);

            McQuestion question = new McQuestion().builder().category(category).categoryId(categoryId).content(q).build();

            boolean correct1 = false, correct2 = false, correct3 = false, correct4 = false;
            if (correctOption == 1) {
                correct1 = true;
            } else if (correctOption == 2) {
                correct2 = true;
            } else if (correctOption == 3) {
                correct3 = true;
            } else {
                correct4 = true;
            }

            McOption op1 = new McOption().builder().opContent(opt1).question(question).isCorrect(correct1).build();
            McOption op2 = new McOption().builder().opContent(opt2).question(question).isCorrect(correct2).build();
            McOption op3 = new McOption().builder().opContent(opt3).question(question).isCorrect(correct3).build();
            McOption op4 = new McOption().builder().opContent(opt4).question(question).isCorrect(correct4).build();

            session.persist(question);
            session.persist(op1);
            session.persist(op2);
            session.persist(op3);
            session.persist(op4);
            tx.commit();

        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
    }


    @Override
    public void updateQuestion(int categoryId, int questionId, String qContent, List<String> optContent, int isCorrect) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String query = "from McQuestion as q join fetch q.options " +
             "join fetch q.category where q.id = :id";
            McQuestion q = (McQuestion) session.createQuery(query).setParameter("id", questionId).list().get(0);

            // update category
            if (categoryId != 0 && categoryId != q.getCategory().getId()) {
                Category cate = session.get(Category.class, categoryId);
                q.setCategory(cate);
                q.setCategoryId(categoryId);
            }

            // update question content
            if (qContent.length() != 0) {
                q.setContent(qContent);
            }

            // update options
            List<McOption> optionList = q.getOptions();

            for (int i = 0; i < optContent.size(); ++i) {
                McOption op = optionList.get(i);

                // update the correct answer
                if (isCorrect != 0) { // isCorrect = 0: correct option does not change
                    if ((i + 1) == isCorrect) { // isCorrect 1->4
                        op.setCorrect(true);
                    } else {
                        op.setCorrect(false);
                    }
                }

                if (optContent.get(i).length() != 0) { // there is new content to update
                    op.setOpContent(optContent.get(i));
                }
                session.update(op);
            }

            session.update(q);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
    }


    @Override
    public void disableQuestion(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        McQuestion q = session.get(McQuestion.class, id);
        q.setDisable(true);
        tx.commit();
    }

    public User getUserbyId(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        User user = session.get(User.class, id);
        tx.commit();
        return user;
    }

    public Submission getSubmissionById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();

        Query query = session.createQuery("from Submission s join fetch s.user " +
                "join fetch s.category " +
                "join fetch s.quiz as quiz " +
                 "join fetch quiz.selectectList " +
                "where s.id = :id");
        Submission sub = (Submission) query.setParameter("id", id).list().get(0);
        tx.commit();
        return sub;
    }

    public McQuiz getQuizById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("from McQuiz quiz join fetch quiz.questionList as q " +
                "where quiz.id = :id");
        McQuiz quiz = (McQuiz) query.setParameter("id", id).list().get(0);
        tx.commit();
        return quiz;
    }

    public McQuestion getQuestionById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("from McQuestion q " +
                "join fetch q.options " +
                 "join fetch q.category " +
                "where q.id = :id");
        McQuestion q = (McQuestion) query.setParameter("id", id).list().get(0);
        tx.commit();
        return q;
    }
}
