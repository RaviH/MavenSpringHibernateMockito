package com.mayabansi.webappdemo.dao;

import com.mayabansi.webappdemo.domain.Book;
import com.mayabansi.webappdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 1:38:24 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository("bookDao")
public class BookDaoHibernate extends GenericDaoHibernate<Book, Long> implements BookDao {

    public BookDaoHibernate() {
        super(Book.class);
    }

    public List<Book> getSpecialPromotionsBasedOnUser(User user) {
        final Book[] bookArr = new Book[]{
                new Book("Special Book #1", 25.00D, 2005)
        };

        return Arrays.asList(bookArr);
    }

    public List<Book> getTop5BooksOnSale() {
        final Book[] bookArr = new Book[]{
                new Book("Top Book #1", 25.00D, 2005),
                new Book("Top Book #2", 50.00D, 2005),
                new Book("Top Book #3", 75.00D, 2005),
                new Book("Top Book #4", 100.00D, 2005),
                new Book("Top Book #5", 125.00D, 2005)
        };

        return Arrays.asList(bookArr);
    }
}
