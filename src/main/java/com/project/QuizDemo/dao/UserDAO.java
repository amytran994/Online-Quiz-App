package com.project.QuizDemo.dao;

import com.project.QuizDemo.domain.User;

import java.io.Serializable;

public interface UserDAO extends Serializable {

    // return 1 if successfully register
    // return 0 if this username already exists
    int register(String username, String password, String firstname, String lastname,
                 String email, String address, String phoneNumber, boolean isAdmin);

    // return 1 if successfully login
    // return 0 if this username does not exist
    User login(String username, String password);
    User getUserbyID(int id);
}
