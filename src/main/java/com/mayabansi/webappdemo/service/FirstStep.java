package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.Cart;
import com.mayabansi.webappdemo.domain.CartItem;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 11:58:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FirstStep {

    Logger log = Logger.getLogger(FirstStep.class);

    public boolean addToCart(final Cart cart, final CartItem cartItem) {
        log.info("Adding to cart...doing some funky business processing.");
        cart.addToCart(cartItem);
        return false;
    }

    public void someVeryImportantBusinessMethod() {
        log.info("someVeryImportantBusinessMethod.");
    }
}
