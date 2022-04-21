package com.project.QuizDemo.dao.impl;

import com.project.QuizDemo.dao.QuizDAO;
import com.project.QuizDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;


import java.util.ArrayList;
import java.util.List;

@Repository("QuizJdbcDAO")
public class QuizJdbcDAOImpl implements QuizDAO {

    // JdbcTemplate needed
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // end JdbcTemplate

    @Override
    public List<McQuestion> getQuestionList(String category) {

        // get 10 question by category
        String query = "select q.id as id, q.q_content as q_content, c.id as category_id " +
                "from question q inner join category c on q.category_id = c.id " +
                "where c.name = :category and q.disable=false " +
                "order by rand() limit 10";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("category", category);

        List<McQuestion> qList = namedParameterJdbcTemplate.query(query, parameterSource, new QuestionRowMapper());

        // get options for each question by question_id
        String getOptionsbyQuestionId = "select * from options where question_id = :id";

        for (int i=0; i<qList.size(); ++i){

            McQuestion q = qList.get(i);
            int id = q.getId();
            parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("id", id);
            List<McOption> opList = namedParameterJdbcTemplate.query(getOptionsbyQuestionId,
            parameterSource, new OptionRowMapper());

            q.setOptions(opList);

            // get correct option
            for (int j=0; j<opList.size(); ++j) {
                if (opList.get(j).isCorrect()) {
                    q.setCorrect(j+1);
                }
            }
        }

        return qList;
    }


    @Override
    public void submitQuiz(List<McQuestion> qList, int[] selectedArray, int score,
                           String startTime, String endTime, User user) {

        // quiz insert using simpleJdbcInsert to get quiz_id
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        String quizName = user.getFirstname() + " " + user.getLastname() + " " + startTime;

        parameterSource.addValue("category_id", qList.get(0).getCategoryId());
        parameterSource.addValue("name", quizName);

        this.simpleJdbcInsert = new SimpleJdbcInsert(this.jdbcTemplate);
        simpleJdbcInsert.withTableName("quiz").usingGeneratedKeyColumns("id");
        Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
        int quizId = key.intValue();

//        int categoryId = qList.get(0).getCategory_id();
//        Category category = createCategory(categoryId);
//        List<McOption> selectedList = createSelectedOptionList(qList, selectedArray);

//        McQuiz quiz = createQuiz(qList, selectedList, category, quizId, quizName);

        // quiz_question insert (relation table)
        String quiz_question_insert = "insert into quiz_question(question_id, selected_option_id, quiz_id)" +
         " values (?, ?, ?)";
        int questionId = 0, selectedId = 0;
        McQuestion q;
        McOption selected;

        for (int i = 0; i < qList.size(); ++i) {

            q = qList.get(i);
            questionId = q.getId();

            if (selectedArray[i] != 0) {
                selected = q.getOptions().get(selectedArray[i]-1);
                selectedId = selected.getId();
                jdbcTemplate.update(quiz_question_insert, questionId, selectedId, quizId);

            } else {
//              // user did not select any option, selected_id = null
                String quiz_question_insert2 = "insert into quiz_question(question_id, quiz_id)" +
                        " values (?, ?)";
                jdbcTemplate.update(quiz_question_insert2, questionId, quizId);

            }
        }

        // submission insert

//        parameterSource = new MapSqlParameterSource();
//
//        parameterSource.addValue("user_id", user.getId());
//        parameterSource.addValue("starttime", startTime);
//        parameterSource.addValue("endtime", endTime);
//        parameterSource.addValue("score", score);
//        parameterSource.addValue("quiz_id", quizId);
//
//
//        simpleJdbcInsert.withTableName("submission").usingGeneratedKeyColumns("id");
//        Number submissionKey = simpleJdbcInsert.executeAndReturnKey(parameterSource);
//        int submissionId = submissionKey.intValue();
//
//        Submission submission = createSubmissionObj(submissionId, startTime, endTime, score, user,quiz);
//
//        return submission;
//
        String submission_insert = "insert into submission(starttime, endtime, score, user_id, quiz_id, category_id)" +
                " values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(submission_insert, startTime, endTime, score, user.getId(), quizId, qList.get(0).getCategoryId());

    }


    // Helper for submitQuiz()
    private Category createCategory(int id) {
        return new Category().builder().id(id).build();
    }

    // Helper for submitQuiz()
    private List<McOption> createSelectedOptionList(List<McQuestion> qList, int[] selectedArray) {

        List<McOption> selected = new ArrayList<>();

        for (int i = 0; i < qList.size(); ++i) {

            McOption op = null;
            int selectedIndex = selectedArray[i];

            if (selectedIndex != 0) {
                op = qList.get(i).getOptions().get(selectedIndex);
            }

            selected.add(op);
        }
        return selected;
    }

    // Helper for submitQuiz()
    private McQuiz createQuiz(List<McQuestion> qList, List<McOption> selected, Category category, int id, String name) {

        return new McQuiz().builder().name(name).id(id).selectectList(selected).questionList(qList).build();
    }
    private Submission createSubmissionObj(int id, String startTime, String endTime, int score, User user, McQuiz quiz) {

        return new Submission().builder().id(id).startTime(startTime).endTime(endTime).score(score).user(user).quiz(quiz).build();
    }
}
