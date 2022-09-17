package com.duy.thesisManagement.thesis.dao;

import com.duy.thesisManagement.thesis.model.Faculty;
import com.duy.thesisManagement.thesis.model.Thesis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Faculty> getFaculty() {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder b = currentSession.getCriteriaBuilder();
        CriteriaQuery<Faculty> q = b.createQuery(Faculty.class);

        Root root = q.from(Faculty.class);

        q = q.select(root);
        q = q.orderBy(b.desc(root.get("name")));

        Query query = currentSession.createQuery(q);

        return query.getResultList();
    }

    @Override
    public boolean createdFaculty(Faculty faculty) {
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            currentSession.save(faculty);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteFaculty(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            Faculty faculty = currentSession.get(Faculty.class, id);
            currentSession.delete(faculty);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
