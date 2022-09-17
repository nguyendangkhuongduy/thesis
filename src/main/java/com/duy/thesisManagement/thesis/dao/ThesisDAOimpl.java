package com.duy.thesisManagement.thesis.dao;

import com.duy.thesisManagement.thesis.model.Thesis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ThesisDAOimpl implements ThesisDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Thesis> getThesis() {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder b = currentSession.getCriteriaBuilder();
        CriteriaQuery<Thesis> q = b.createQuery(Thesis.class);

        Root root = q.from(Thesis.class);
        q = q.select(root);

        q = q.orderBy(b.desc(root.get("createdDate")));

        Query query = currentSession.createQuery(q);


        return query.getResultList();
    }

    @Override
    public boolean createdThesis(Thesis thesis) {
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            currentSession.saveOrUpdate(thesis);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }


    @Override
    public Thesis getThesisById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Thesis thesis = currentSession.get(Thesis.class, id);

        return thesis;
    }

    @Override
    public boolean deleteThesis(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            Thesis thesis = currentSession.get(Thesis.class, id);
            currentSession.delete(thesis);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
