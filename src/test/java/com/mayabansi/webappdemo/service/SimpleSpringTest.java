package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.dao.CartDao;
import com.mayabansi.webappdemo.domain.Cart;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Nov 27, 2010
 * Time: 12:00:45 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
// specifies the Spring configuration to load for this test fixture
@ContextConfiguration({"classpath:app-config.xml"})
public class SimpleSpringTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CartDao cartDao;

    @Test
    public void testSave() throws Exception {
        Cart cart = new Cart();
        cart.setTotal(250.0d);
        cart.setTotalPrice(250.0d);
        cart.setTotalQty(2);
        cart.setTotalTax(10.20d);
        cartDao.save(cart);

        Cart savedCart = cartDao.findAll().get(0);
        assertNotNull(savedCart);
    }
}
