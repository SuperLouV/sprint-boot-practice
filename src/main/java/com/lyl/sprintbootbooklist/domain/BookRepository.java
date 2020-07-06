package com.lyl.sprintbootbooklist.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yilinlou
 * @date 5/27/20 9:56 下午
 * @Email:ylou7@stevens.edu
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthorAndStatus(String author, int statuis);

    List<Book> findByDescriptionEndsWith(String des);

    //    @Query("select b from Book b where length(b.name)>?1 ")
    @Query(value = "select * from book where length(name)>?1 ",nativeQuery = true)      //nativeQuery=true 采用原生SQL语句
    List<Book> findByJPQL(int len);

    @Transactional
    @Modifying
    @Query("update Book b set b.status=?1 where id=?2")
    int uodateByJPQL(int status,long id);

    /**
    * @Description: 自定义删除
    * @Param: [id]
    * @return: int
    * @Author: Yilin Lou
    * @Date: 5/31/20
    */
    @Transactional
    @Modifying
    @Query("delete from Book b where b.id=?1")
    int deleteByJPQL(long id);

}
