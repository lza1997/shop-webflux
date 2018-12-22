package com.lza.BaseController;

import com.lza.entity.Label;
import com.lza.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class BaseController {

    @Autowired
    private LabelService labelService;
    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public Result save(@RequestBody Label label){
       labelService.save(label);
        return new Result(true,StatusCode.OK,  "保存成功");
    }

    @GetMapping
    public Result findAll(){
       //
        String header = request.getHeader("Authorization");
        System.out.println(header);

       List<Label> list = labelService.findAll();
        return new Result(true, StatusCode.OK,  "查询成功",list);
    }

    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable("labelId") String id){
        System.out.println("222222222222222");
        Label label = labelService.findById(id);
        return new Result(true,StatusCode.OK, "查询成功",label);
    }

    @PutMapping(value = "/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,  "修改成功");
    }

    @DeleteMapping(value = "/{labelId}")
    public Result delete(@PathVariable String labelId){
       labelService.delete(labelId);
        return new Result(true,StatusCode.OK,  "删除成功");
    }

    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> list = labelService.findSearch(label);
        System.out.println(list);
        return new Result(true,StatusCode.OK,  "查询成功",list);
    }

    @GetMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@PathVariable int page, @PathVariable int size, @RequestBody Label label){
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true,StatusCode.OK, "查询成功",
                new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }
}
