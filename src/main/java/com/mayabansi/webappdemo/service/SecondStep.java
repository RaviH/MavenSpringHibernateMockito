package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.Cart;
import com.mayabansi.webappdemo.domain.CartItem;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 11:59:17 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SecondStep {

    Logger log = Logger.getLogger(SecondStep.class);

    public boolean updateCartTotal(final Cart cart, final CartItem cartItem) {
        log.info("Please update cart total...doing some funky business processing here.");
        return false;
    }

    public void someVeryImportantBusinessMethod() {
        log.info("someVeryImportantBusinessMethod.");
    }
}
