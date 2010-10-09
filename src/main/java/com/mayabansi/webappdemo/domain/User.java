package com.mayabansi.webappdemo.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Oct 1, 2010
 * Time: 1:23:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    private Long id;
    private String email;

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
}
