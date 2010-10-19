package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 1:41:27 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class DemoSpyTest {

    @Spy
    ProfileService profileService = new ProfileService();

    @Test
    public void demoSpy() {
        final User user = new User("ravi");
        user.setEmail("ravi@gmail.com");

        // Stub spy
        doReturn(user).when(profileService).getUser();

        profileService.changeEmail("ravi.hasija@gmail.com");

        assertEquals("ravi.hasija@gmail.com", user.getEmail());
    }
}
