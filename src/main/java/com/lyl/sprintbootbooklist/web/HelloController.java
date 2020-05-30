package com.lyl.sprintbootbooklist.web;

import com.lyl.sprintbootbooklist.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yilinlou
 * @date 5/18/20 9:00 下午
 * @Email:ylou7@stevens.edu
 */

@RestController     //次注释用于定义身份,通过浏览器的某个地址调用函数
//@Controller         //找模版,可以返回页面
@RequestMapping("/api/v2")
public class HelloController {

//    @Autowired
//    private Book book;
    //    @RequestMapping(value = "/say",method = RequestMethod.GET)
//    @RequestMapping("/say")
    @PostMapping("/say")        //简写
    public String hello() {
        return "hellp spring boot";
    }

    @GetMapping("/books")
    @ResponseBody       //加上之后可以返回字符串 or 实体
    public Object getAll(@RequestParam("page") int page,@RequestParam ("size")int size) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", "Internet");
        book.put("isbn", "1232131");
        book.put("author", "lyl");
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "十万个为什么");
        book2.put("isbn", "324342");
        book2.put("author", "lll");


        List<Map> ls=new ArrayList<>();
        ls.add(book);
        ls.add(book2);
        Map<String, Object> pagemap = new HashMap<>();
        pagemap.put("page", page);
        pagemap.put("size", size);
        pagemap.put("content",ls);
        return pagemap;
    }

    @GetMapping("/books/{id}")      //括号里表示的就是下面的👇参数，要一致
    //可以传入正则表达式
//    public Object getOne(@PathVariable("id") long id)  //或者通过映射参数

    public Object getOne(@PathVariable long id) {
//        System.out.println("id : " + id);
//        Map<String, Object> book = new HashMap<>();
//        book.put("name", "Internet");
//        book.put("isbn", "sdadasd");
//        book.put("author", "lyl");
//        book.put("user", username);
        return null;
    }


    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam String author,
                       @RequestParam String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("name",name);
        book.put("author",author);
        book.put("isbn",isbn);
        return book;
    }

}
