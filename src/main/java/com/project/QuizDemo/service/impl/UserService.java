package com.project.QuizDemo.service.impl;

import com.project.QuizDemo.dao.UserDAO;
import com.project.QuizDemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    private UserDAO userJdbcDao;

    @Autowired
    public void setUserJdbcDao(UserDAO userJdbcDao) {
        this.userJdbcDao = userJdbcDao;
    }

    public User login(String username, String password) {
        return this.userJdbcDao.login(username, password);
    }

    public int register(String username, String password, String firstname, String lastname,
    String email, String address, String phoneNumber, boolean isAdmin) {
        return this.userJdbcDao.register(username, password, firstname, lastname, email, address, phoneNumber, isAdmin);
    }

}
