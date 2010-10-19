package com.mayabansi.webappdemo.service;

import com.mayabansi.webappdemo.domain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Sep 19, 2010
 * Time: 10:42:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WeeklySpecialsService {

    public List<Book> applyWeeklySpecials(final List<Book> bookList) {

        for (int i = 0; i < bookList.size(); i++) {
            final Book book = bookList.get(i);
            final Double orgPrice = book.getPrice();
            double newPrice = orgPrice;

            if (orgPrice < 50D) {
                // Apply 30% discount.
                newPrice = 0.70 * orgPrice;
            } else if (orgPrice < 20D && orgPrice < 49.99D) {
                // Apply 20% discount.
                newPrice = 0.80 * orgPrice;
            } else {
                // Apply 10% discount.
                newPrice = 0.90 * orgPrice;
            }

            book.setPrice(newPrice);
            bookList.set(i, book);
        }

        return bookList;
    }
}
