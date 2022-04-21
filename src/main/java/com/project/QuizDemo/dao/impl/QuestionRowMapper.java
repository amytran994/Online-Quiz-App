package com.project.QuizDemo.dao.impl;

import com.project.QuizDemo.domain.McOption;
import com.project.QuizDemo.domain.McQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<McQuestion> {

    @Override
    public McQuestion mapRow(ResultSet resultSet, int i) throws SQLException {

        McQuestion question = new McQuestion();

        question.setContent(resultSet.getString("q_content"));
        question.setId(resultSet.getInt("id"));
        question.setCategoryId(resultSet.getInt("category_id"));

        return question;
    }
}


