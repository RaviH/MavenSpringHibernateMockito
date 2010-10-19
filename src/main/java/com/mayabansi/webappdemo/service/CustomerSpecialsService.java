package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.dao.BookDao;
import com.mayabansi.webappdemo.dao.CustomerDao;
import com.mayabansi.webappdemo.domain.Book;
import com.mayabansi.webappdemo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 10:30:26 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CustomerSpecialsService {

    CustomerDao customerDao;
    BookDao bookDao;

    @Autowired
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    public List<Book> applySpecials(List<Book> bookList, User user) {

        if (customerDao.isCustomerSpecial(user.getId())) {
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                Double price = book.getPrice();
                Double priceOff = price * 0.10;
                Double newPrice = price - priceOff;
                book.setPrice(newPrice);
                bookList.set(i, book);
            }
        }

        return bookList;
    }

    public List<Book> getSpecials() {
        final Book[] booksOnSpecialPromotionArr = new Book[]{
                new Book("Special #1", 20.00D, 2005),
                new Book("Special #2", 10.00D, 2010),
                new Book("Special #3", 100.00D, 2006)
        };

        return Arrays.asList(booksOnSpecialPromotionArr);
    }
}
