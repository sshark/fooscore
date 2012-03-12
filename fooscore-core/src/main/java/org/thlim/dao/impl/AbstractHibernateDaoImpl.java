package org.thlim.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.thlim.dao.Dao;

public abstract class AbstractHibernateDaoImpl<T extends Serializable> implements Dao<T>
{

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractHibernateDaoImpl()
    {
        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void delete(T object)
    {
        getCurrentSession().delete(object);
    }


    @SuppressWarnings("unchecked")
    public T load(long id)
    {
        return (T) getCurrentSession().get(clazz, id);
    }

    @Transactional
    public void save(T object)
    {
        getCurrentSession().save(object);

    }

    @Transactional
    public void update(T object)
    {
        getCurrentSession().update(object);

    }

    @Transactional
    public List<T> findAll()
    {
        Criteria criteria = getCurrentSession().createCriteria(clazz);
        return (List<T>)criteria.list();
    }


    @Transactional
    public int countAll()
    {
        Criteria criteria = getCurrentSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        return ((Long)criteria.uniqueResult()).intValue();
    }

    protected Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

}
