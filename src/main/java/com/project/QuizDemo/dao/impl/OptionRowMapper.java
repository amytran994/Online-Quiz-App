package com.project.QuizDemo.dao.impl;

import com.project.QuizDemo.domain.McOption;
import com.project.QuizDemo.domain.McQuestion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionRowMapper implements RowMapper<McOption> {

    @Override
    public McOption mapRow(ResultSet resultSet, int i) throws SQLException {
//        private Integer id;
//        private String opContent;
//        private boolean isCorrect;
//        private int questionId;

        McOption option = new McOption();

        option.setId(resultSet.getInt("id"));
        option.setOpContent(resultSet.getString("op_content"));
        option.setCorrect(resultSet.getBoolean("iscorrect"));
        option.setQuestionId(resultSet.getInt("question_id"));

        return option;
    }
}


