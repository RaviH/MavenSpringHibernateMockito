package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.dao.CartDao;
import com.mayabansi.webappdemo.domain.Cart;
import com.mayabansi.webappdemo.domain.CartItem;
import com.mayabansi.webappdemo.domain.MainOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 12:18:15 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderProcessServiceTest {

    @Mock
    MainOrder order;
    @Mock
    FirstStep firstStep;
    @Mock
    SecondStep secondStep;
    @Mock
    ThirdStep thirdStep;
    @Mock
    CartDao cartDao;

    @Before
    public void before() {
        when(cartDao.get(1L)).thenReturn(new Cart(13.0D, 2.76D, 4, 3D));
        when(order.getTotalAmount()).thenReturn(new BigDecimal(5));
        when(firstStep.addToCart(Matchers.<Cart>anyObject(), Matchers.<CartItem>anyObject())).thenReturn(true);
        when(secondStep.updateCartTotal(Matchers.<Cart>anyObject(), Matchers.<CartItem>anyObject())).thenReturn(true);
        when(thirdStep.justOrderIt(Matchers.<Cart>anyObject())).thenReturn(true);
    }

    @Test
    public void show_How_InOrder_Works() {
        // Stubbing was done in before method
        OrderProcessService orderProcessService = new OrderProcessService()
                .setFirstStep(firstStep)
                .setSecondStep(secondStep)
                .setThirdStep(thirdStep)
                .setCartDao(cartDao);


        BigDecimal b = orderProcessService.order(order);

        InOrder inOrder = inOrder(firstStep, thirdStep);

        inOrder.verify(firstStep).addToCart(Matchers.<Cart>anyObject(), Matchers.<CartItem>anyObject());
        //inOrder.verify(secondStep).updateCartTotal(Matchers.<Cart>anyObject(), Matchers.<CartItem>anyObject());
        inOrder.verify(thirdStep).justOrderIt(Matchers.<Cart>anyObject());

        assertEquals(new BigDecimal(25), b);
    }
}