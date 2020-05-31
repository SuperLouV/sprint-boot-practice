package com.lyl.sprintbootbooklist.service;

import com.lyl.sprintbootbooklist.domain.Book;
import com.lyl.sprintbootbooklist.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author Yilinlou
 * @date 5/27/20 9:59 下午
 * @Email:ylou7@stevens.edu
 */

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    /**
     * 新增一个
     *
     * @param book
     * @return
     */

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    /**
     * 获取一条信息
     *
     * @param id
     * @return
     */
    public Book findOne(long id) {
        return bookRepository.findById(id).get();
    }
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> findByAuthorAndStatus(String author,int status){
        return bookRepository.findByAuthorAndStatus(author,status);
    }

    public List<Book> findByDescriptionEndsWith(String des){
        return bookRepository.findByDescriptionEndsWith (des);
    }

    public List<Book> findByJPQL(int len){
        return bookRepository.findByJPQL(len);
    }

}
