package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 1:39:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProfileService extends GenericService {

    public void changeEmail(String email) {
        User user = getUser();
        user.setEmail(email);
    }
}
