package com.lyl.sprintbootbooklist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yilinlou
 * @date 5/27/20 9:56 下午
 * @Email:ylou7@stevens.edu
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int statuis);

    List<Book> findByDescriptionEndsWith(String des);

    //    @Query("select b from Book b where length(b.name)>?1 ")
    @Query(value = "select * from book where length(name)>?1 ",nativeQuery = true)      //nativeQuery=true 采用原生SQL语句
    List<Book> findByJPQL(int len);

}
