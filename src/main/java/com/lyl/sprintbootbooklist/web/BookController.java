package com.lyl.sprintbootbooklist.web;

import com.lyl.sprintbootbooklist.domain.Book;
import com.lyl.sprintbootbooklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Yilinlou
 * @date 5/31/20 7:46 下午
 * @Email:ylou7@stevens.edu
 */

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * @Description: 获取全部书单列表
     * @Param: [model]
     * @return: java.lang.String
     * @Author: Yilin Lou
     * @Date: 6/11/20
     */
    @GetMapping("/books")
    public String list(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
//        List<Book> books = bookService.findAll();
        Page<Book> page1 = bookService.findAllByPages(pageable);
        model.addAttribute("page", page1);
        return "books";     //返回对应模版名字，HTML
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {

        Book book = bookService.findOne(id);
        if (book == null) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * @Description: 跳转input新增页面
     * @Param: []
     * @return: java.lang.String
     * @Author: Yilin Lou
     * @Date: 7/1/20
     */
    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";

    }

    /**
     * @Description: 带着id跳转到更新页面input
     * @Param: []
     * @return: java.lang.String
     * @Author: Yilin Lou
     * @Date: 7/5/20
     */
    @GetMapping("/books/{id}/input")
    public String inputEditPage(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        System.out.println(book.getStatus());
        model.addAttribute("book", book);
        return "input";

    }


    @PostMapping("/books")
    public String post(Book book, final RedirectAttributes attributes) {

        Book book1 = bookService.save(book);
        if (book1 != null)
            attributes.addFlashAttribute("message", "《" + book1.getName() + "》信息提交成功");

        return "redirect:/books";   //返回这个url 一个GetMapping
    }

    /**
     * POST -->redirect GET 两次请求 model只是一个作用域
     * 所以用到了Flash
     */

    @GetMapping("/books/{id}/delete")
    public String delete(@PathVariable long id, final RedirectAttributes attributes) {

        bookService.delete(id);
        attributes.addFlashAttribute("message",  "信息delete成功");

        return "redirect:/books";   //返回这个url 一个GetMapping
    }


}
