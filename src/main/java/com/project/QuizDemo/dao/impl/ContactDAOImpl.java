package com.project.QuizDemo.dao.impl;

import com.project.QuizDemo.dao.ContactDAO;
import com.project.QuizDemo.domain.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ContactDAOImpl implements ContactDAO {

    @Override
    public List<Contact> getAllContact() {
        List<Contact> contactList = new ArrayList<>();

        contactList.add(new Contact("Admin1", "admin1@gmail.com", "USA"));
        contactList.add(new Contact("Admin2", "admin2@yahoo.com", "USA"));

        return contactList;
    }
}
