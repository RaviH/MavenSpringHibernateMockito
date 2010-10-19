package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.User;

import javax.servlet.http.HttpSession;


/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 25, 2010
 * Time: 1:23:55 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericService {

    private final static ThreadLocal<HttpSession> session = new ThreadLocal<HttpSession>();


    public User getUser() {
        return (User) session.get().getAttribute("user");
    }
}
