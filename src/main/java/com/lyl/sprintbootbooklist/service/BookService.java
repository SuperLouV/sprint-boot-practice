package com.lyl.sprintbootbooklist.service;

import com.lyl.sprintbootbooklist.domain.Book;
import com.lyl.sprintbootbooklist.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Book> findAllByPages(Pageable pageable){
        return bookRepository.findAll(pageable);
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

    public int updatByJPQL(int status,long id){
        return bookRepository.uodateByJPQL(status,id);
    }
    public int deleteByJPQL(long id){
        return bookRepository.deleteByJPQL(id);
    }

    @Transactional      //定义方法为一个事务
    public int deleteAndUpdate(long id,int status,long uid){
        int dcount= bookRepository.deleteByJPQL(id);
        int ucount=bookRepository.uodateByJPQL(status, uid);
        return dcount+ucount;
    }

}
