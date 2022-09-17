package com.duy.thesisManagement.thesis.dao;

import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.Thesis;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PositionDAOImpl implements PositionDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Position> getPosition() {
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder b = currentSession.getCriteriaBuilder();
        CriteriaQuery<Position> q = b.createQuery(Position.class);

        Root root = q.from(Position.class);
        q = q.select(root);

        q = q.orderBy(b.desc(root.get("name")));

        Query query = currentSession.createQuery(q);


        return query.getResultList();
    }

    @Override
    public boolean createdCouncil(Position position) {
        Session currentSession = sessionFactory.getCurrentSession();

        try {
            currentSession.save(position);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
