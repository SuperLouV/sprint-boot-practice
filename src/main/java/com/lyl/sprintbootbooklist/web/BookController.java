package com.lyl.sprintbootbooklist.web;

import com.lyl.sprintbootbooklist.domain.Book;
import com.lyl.sprintbootbooklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yilinlou
 * @date 5/31/20 7:46 下午
 * @Email:ylou7@stevens.edu
 */

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(){
        return "books";     //返回对应模版名字，HTML
    }
    @GetMapping("/books/{id}")
    public  String detail(@PathVariable long id, Model model){

        Book book=bookService.findOne(id);
        if (book==null){
            book=new Book();
        }
        model.addAttribute("book",book);
        return "book";

    }
}
