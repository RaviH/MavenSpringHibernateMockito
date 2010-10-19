package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.Cart;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 12:00:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ThirdStep {

    Logger log = Logger.getLogger(ThirdStep.class);

    public boolean justOrderIt(final Cart cart) {
        log.info("Ordering it...ouch!!! You have no choice.");
        return false;
    }

    public void someVeryImportantBusinessMethod() {
        log.info("someVeryImportantBusinessMethod.");
    }

    public void addSomethingToCart(ThirdStepType thirdStepType) {
        log.info("addSomethingToCart was called with: " + thirdStepType);
    }

    public enum ThirdStepType {
        NEW, ALREADY_EXISTS
    }
}
