package com.lza.controller;


import com.lza.pojo.Article;
import com.lza.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    @RequestMapping(value = "sc", method = RequestMethod.GET)
    public Result findByKey(@RequestParam(value="key") String key, @RequestParam(value="page") int page, @RequestParam(value="size") int size){
//        try {
//            System.out.println(key);
//            key = new String(key.getBytes("ISO-8859-1"), "utf8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        Page<Article> pageData = articleService.findByKey(key, page, size);
        System.out.println(pageData);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/{key}/{page}/{size}", method = RequestMethod.GET)
    public Result findByKey11(@PathVariable String key, @PathVariable int page, @PathVariable int size){
        Page<Article> pageData = articleService.findByKey(key, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Article>(pageData.getTotalElements(), pageData.getContent()));
    }


}
