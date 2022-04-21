package com.project.QuizDemo.dao;

import com.project.QuizDemo.dao.impl.AdminHibernateDAOImpl;
import com.project.constants.ImplTypes;

public class AdminDAOFactory {

    public static AdminDAO getAdminDAO(ImplTypes type)
    {
        switch(type){
            case HIBERNATE:
                return new AdminHibernateDAOImpl();
            default:
                throw new UnsupportedOperationException("Type is not supported");
        }
    }
}
