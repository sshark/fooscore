package org.thlim.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thlim.dao.Dao;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateDaoImpl<T extends Serializable> implements Dao<T>
{

    private Class<T> domainClass;
    private static Logger logger = LoggerFactory.getLogger(AbstractHibernateDaoImpl.class);


    public AbstractHibernateDaoImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    private SessionFactory sf;


    public SessionFactory getSessionFactory() {
        return sf;
    }


    public void setSessionFactory(SessionFactory sf) {
        this.sf = sf;
    }


    public void delete(T object) {
        getSession().delete(object);
    }


    @SuppressWarnings("unchecked")
    public T load(long id) {
        return (T) getSession().get(domainClass, id);
    }


    // public void save(T object) {
    // getSession().saveOrUpdate(object);
    // }

    public void save(T object) {
        try {
            // Using open session
            Session session = sf.openSession();
            // NOTE sf.openSession() WORKS, but sf.getCurrentSession() DOES NOT
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();

            // //Using current session
            // Session session = sf.getCurrentSession();
            // session.beginTransaction();
            // session.save(object);
            // session.getTransaction().commit();

        } catch (Exception e) {
            logger.warn(
                    String.format("Caught an exception when trying to save a %s record", object.getClass().getSimpleName()), e);
        } finally {
        }
    }// end-of-save


    public void update(T object) {
        try {
            // Using open session
            Session session = sf.openSession();
            // NOTE sf.openSession() WORKS, but sf.getCurrentSession() DOES NOT
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();

            // //Using current session
            // Session session = sf.getCurrentSession();
            // session.beginTransaction();
            // session.save(object);
            // session.getTransaction().commit();

        } catch (Exception e) {
            logger.warn(
                    String.format("Caught an exception when trying to save a %s record", object.getClass().getSimpleName()), e);
        } finally {
        }
    }


    public List<T> findAll() {
        Criteria criteria = getSession().createCriteria(domainClass);
        return (List<T>) criteria.list();
    }


    public int countAll() {
        Criteria criteria = getSession().createCriteria(domainClass);
        criteria.setProjection(Projections.rowCount());
        return ((Long) criteria.uniqueResult()).intValue();
    }


    public Session getSession() {
        // presumes a current session, which we have through the
        // OpenSessionInViewFilter; doesn't work without that
        return sf.getCurrentSession();
    }
}
