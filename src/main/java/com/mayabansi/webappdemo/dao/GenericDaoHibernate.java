package com.mayabansi.webappdemo.dao;

import javassist.tools.rmi.ObjectNotFoundException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import java.io.Serializable;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Ravi Hasija
 * Date: Aug 2, 2009
 * Time: 4:47:40 PM
 * <p/>
 * Modification History:
 * <p/>
 * 09/19/2009  RHasija  Fixed findAll(startIndex, maxResult).
 */
public abstract class GenericDaoHibernate<Domain, PrimaryKey extends Serializable> implements GenericDao<Domain, PrimaryKey> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<Domain> type;
    private final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();


    public GenericDaoHibernate(final Class<Domain> type) {
        this.type = type;
    }


    public Session getSession() {
        Session s = sessionThreadLocal.get();
        if (s == null || !s.isOpen()) {
            s = SessionFactoryUtils.getSession(sessionFactory, true);
            sessionThreadLocal.set(s);
        }
        return s;
    }


    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(final Domain entity) throws Exception {
        getSession().saveOrUpdate(entity);
    }


    @SuppressWarnings("unchecked")
    public Domain findByPrimaryKey(final PrimaryKey id) {
        return (Domain) getSession().get(type, id);
    }

    public Domain get(final PrimaryKey id) {
        return (Domain) getSession().get(type, id);
    }


    public void delete(final Domain o) throws Exception {
        getSession().delete(o);
    }

    public void remove(final PrimaryKey id) {
        getSession().delete(get(id));
    }


    @SuppressWarnings("unchecked")
    public List<Domain> findAll() {
        final Criteria crit = getSession().createCriteria(type);
        return crit.list();
    }


    @SuppressWarnings("unchecked")
    public List<Domain> findAll(final int startIndex, final int maxResults) {
        final Criteria crit = getSession().createCriteria(type);
        crit.setFirstResult(startIndex);
        crit.setMaxResults(maxResults);
        return crit.list();
    }


    @SuppressWarnings("unchecked")
    public List<Domain> findByExample(final Domain exampleInstance, final String[] excludeProperties) {
        final Criteria crit = getSession().createCriteria(type);
        final Example example = Example.create(exampleInstance);


        if (excludeProperties != null) {
            for (final String property : excludeProperties) {
                example.excludeProperty(property);
            }
        }
        crit.add(example);
        return crit.list();
    }


    public void flush() {
        getSession().flush();
    }


    void throwIfNull(final Object obj, final String message) throws ObjectNotFoundException {
        if (obj == null) {
            throw new ObjectNotFoundException(message);
        }
    }
}