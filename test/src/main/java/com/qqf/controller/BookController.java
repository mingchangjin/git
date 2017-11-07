package com.qqf.controller;

import com.qqf.dao.BookDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/6 0006.
 */
@RestController
public class BookController {
    @Resource
    private BookDao bd;
    @RequestMapping("print")
    public int print(){
        Map data = new HashMap();
        data.put("x",99);
        bd.first(data);
        return (int)data.get("y");

    }
    @RequestMapping("printt")
    public String printt(){
        Map data = new HashMap();


        data.put("x",11);
        bd.first1(data);
        return (String)data.get("y");


    }


}
