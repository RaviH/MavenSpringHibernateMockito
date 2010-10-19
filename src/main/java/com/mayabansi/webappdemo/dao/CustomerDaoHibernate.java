package com.mayabansi.webappdemo.dao;

import com.mayabansi.webappdemo.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 1:45:56 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao {

    public CustomerDaoHibernate() {
        super(Customer.class);
    }

    public Boolean isCustomerSpecial(Long customerId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
