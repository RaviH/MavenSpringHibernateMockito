package com.mayabansi.webappdemo.dao;

import com.mayabansi.webappdemo.domain.Book;
import com.mayabansi.webappdemo.domain.User;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 1:35:02 AM
 * To change this template use File | Settings | File Templates.
 */
public interface BookDao extends GenericDao<Book, Long> {

    public List<Book> getSpecialPromotionsBasedOnUser(User user);

    public List<Book> getTop5BooksOnSale();
}
