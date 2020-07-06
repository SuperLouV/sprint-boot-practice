package com.lyl.sprintbootbooklist.web;

import com.lyl.sprintbootbooklist.domain.Book;
import com.lyl.sprintbootbooklist.domain.BookRepository;
import com.lyl.sprintbootbooklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Yilinlou
 * @date 5/27/20 10:00 下午
 * @Email:ylou7@stevens.edu
 */

@RestController     //次注释用于定义身份,通过浏览器的某个地址调用函数
@RequestMapping("/api/v1")
public class BookApp {
    @Autowired
    private BookService bookService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/books")
    public Page<Book> getAll(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable) {
//        return bookService.findAll();
        return bookService.findAllByPages(pageable);
    }


    @PostMapping("/books")
//    public Book post(Book book){      //自动对应实体中参数
//
//    }

    public Book post(@RequestParam String name,
                     @RequestParam String author,
                     @RequestParam String description,
                     @RequestParam int status) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    /**
     * update
     *
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status) {
        Book book = new Book();
        book.setName(name);
        book.setId(id);
        book.setAuthor(author);
        book.setDescription(description);
        book.setId(status);
        return bookService.save(book);
    }

    /**
     * delete
     *
     * @param id
     */
    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id) {
        bookService.delete(id);
    }

//    @PostMapping("/books/by")
//    public List<Book> findByAuthor(@RequestParam String author){
//        return bookService.findByAuthor(author);
//    }
//
//
//    @PostMapping("/books/by")
//    public List<Book> findByAuthorAndStatus(@RequestParam String author,@RequestParam int status){
//        return bookService.findByAuthorAndStatus(author,status);
//    }


    //    @PostMapping("/books/by")
//    public List<Book> findByDescriptionEndsWith(@RequestParam String des){
//        return bookService.findByDescriptionEndsWith(des);
//    }
    @PostMapping("/books/by")
    public int findBy(@RequestParam long id,@RequestParam int status,@RequestParam long uid) {
//        return bookService.findByJPQL(len);
//        return bookService.deleteByJPQL(id);
        return bookService.deleteAndUpdate(id, status, uid);
    }




}
