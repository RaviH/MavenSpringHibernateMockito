package com.mayabansi.webappdemo.dao;

import com.mayabansi.webappdemo.domain.Cart;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Oct 18, 2010
 * Time: 10:25:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("cartDao")
public class CartDaoHibernate extends GenericDaoHibernate<Cart, Long> implements CartDao {

    public CartDaoHibernate() {
        super(Cart.class);
    }
}
