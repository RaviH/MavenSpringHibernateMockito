package com.mayabansi.webappdemo.dao;

import com.mayabansi.webappdemo.domain.Customer;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 1:44:48 AM
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerDao extends GenericDao<Customer, Long> {

    public Boolean isCustomerSpecial(Long customerId);
}
