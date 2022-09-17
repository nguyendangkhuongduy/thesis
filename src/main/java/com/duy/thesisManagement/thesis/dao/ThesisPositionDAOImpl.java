package com.duy.thesisManagement.thesis.dao;


import com.duy.thesisManagement.thesis.model.ThesisPosition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ThesisPositionDAOImpl implements ThesisPositionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean createdThesisPosition(ThesisPosition thesisPosition) {
        return false;
    }

    @Override
    public boolean deleteThesis(int id) {
        return false;
    }
}
