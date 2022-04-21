package com.project.QuizDemo.dao.impl;
import com.project.QuizDemo.dao.UserDAO;

import com.project.QuizDemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserJdbcDAO")
public class UserJdbcDAOImpl implements UserDAO {

    // JdbcTemplate needed
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setSimpleJdbcInsert(SimpleJdbcInsert simpleJdbcInsert) {
        this.simpleJdbcInsert = simpleJdbcInsert;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // end JdbcTemplate

    @Override
    public int register(String username, String password, String firstname, String lastname,
    String email, String address, String phoneNumber, boolean isAdmin) {
        if (existedUser(username) == 1) return 0; // user already existed
        else {

            String query = "insert into user(username, password, firstname, lastname, email, address, phoneNumber, isAdmin, active) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            int res = jdbcTemplate.update(query, username, password, firstname, lastname,
            email, address, phoneNumber, isAdmin, true);

            return res;

        }
    }

    @Override
    public User login(String username, String password) {
        String query = "select * from user where username = :username" +
         " and password = :password";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        parameterSource.addValue("password", password);

        List<User> userList = namedParameterJdbcTemplate.query(query, parameterSource, new UserRowMapper());
        return userList.size() == 0 ? null : userList.get(0);

    }

    private int existedUser (String username) {
        String query = "select count(*) from user where username = :username";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);

        return namedParameterJdbcTemplate.queryForObject(query, parameterSource, Integer.class);

    }

    public User getUserbyID(int id) {
        String query = "select * from user where id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        List<User> userList = namedParameterJdbcTemplate.query(query, parameterSource, new UserRowMapper());
        return userList.size() == 0 ? null : userList.get(0);
    }

}
