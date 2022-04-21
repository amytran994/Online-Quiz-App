package com.project.QuizDemo.service.impl;

import com.project.QuizDemo.dao.FBDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FBService {
    private FBDAO fbJdbcDao;

    @Autowired
    public void setfbJdbcDao(FBDAO fbJdbcDao) {
        this.fbJdbcDao = fbJdbcDao;
    }

    public void addFeedback(String text, int rating) {
        this.fbJdbcDao.addFeedback(text, rating);
    }


}
