package com.mayabansi.webappdemo.controller;

import com.mayabansi.webappdemo.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by IntelliJ IDEA.
 * User: rhasija
 * Date: Oct 12, 2010
 * Time: 9:45:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String getHomePage(Model model) {
        model.addAttribute(new Book());
        return "index";
    }
}
