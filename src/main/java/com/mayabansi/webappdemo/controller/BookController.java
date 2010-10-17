package com.mayabansi.webappdemo.controller;

import com.mayabansi.webappdemo.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Oct 12, 2010
 * Time: 9:19:17 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {
    private Map<Long, Book> books = new ConcurrentHashMap<Long, Book>();

    @RequestMapping(method = RequestMethod.GET)
    public String getCreateForm(Model model) {

        final Book book = new Book();
        book.setTitle("Spring MVC");
        book.setPrice(25.00D);
        book.setYearPublished(2005);
        model.addAttribute(book);

        return "book/createBook";
    }
}
