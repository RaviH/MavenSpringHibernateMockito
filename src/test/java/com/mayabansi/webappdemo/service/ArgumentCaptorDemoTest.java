package com.mayabansi.webappdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 4:07:04 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorDemoTest {

    @Mock
    ThirdStep thirdStep;

    @Test
    public void demo_Argument_Captor() {
        ArgumentCaptor<ThirdStep.ThirdStepType> argumentCaptor = ArgumentCaptor.forClass(ThirdStep.ThirdStepType.class);
        final OrderProcessService orderProcessService = new OrderProcessService();
        orderProcessService.setThirdStep(thirdStep);

        orderProcessService.addToCart(null);

        verify(thirdStep).addSomethingToCart(argumentCaptor.capture());
        assertEquals(ThirdStep.ThirdStepType.NEW, argumentCaptor.getValue());
    }
}
