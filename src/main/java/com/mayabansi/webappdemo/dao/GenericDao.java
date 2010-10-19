package com.mayabansi.webappdemo.dao;

import org.hibernate.Session;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi Hasija
 * Date: Aug 2, 2009
 * Time: 4:32:03 PM
 * <p/>
 * Modification History:
 * <p/>
 * 09/19/2009  RHasija  Fixed findAll(startIndex, maxResult).
 */
@Resource
public interface GenericDao<Domain, PrimaryKey extends Serializable> {


    // Create, Update

    void save(Domain entity) throws Exception;


    // Read

    Domain findByPrimaryKey(PrimaryKey primaryKey);

    // Read

    Domain get(PrimaryKey primaryKey);


    // Delete

    void delete(Domain entity) throws Exception;

    // Delete

    void remove(PrimaryKey primaryKey);

    List<Domain> findAll(int startIndex, int maxResult);


    List<Domain> findAll();


    List<Domain> findByExample(Domain exampleInstance, String[] excludeProperty);


    public Session getSession();

    public void flush();
}