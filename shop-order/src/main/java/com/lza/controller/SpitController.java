package com.lza.controller;

import com.lza.dao.SpitDao;
import com.lza.entity.Spit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/split")
public class SpitController {
//官方推荐方法
    private  final SpitDao spitDao;

    public SpitController(SpitDao spitDao) {
        this.spitDao=spitDao;
    }
   //传统写方法,以数组形式一次性
    @GetMapping("/")
    public Flux<Spit> findAll(){
    return  spitDao.findAll();
    }
    //使用新的stream流的方式，以sse方式，多次返回数据
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   public  Flux<Spit>  getAll(){
        return  spitDao.findAll();
   }
   //新增数据
    @PostMapping("/")
    public Mono<Spit> addspit(@RequestBody Spit spit){
       return spitDao.save(spit);
    }
    //删除数据
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Mono<ResponseEntity<Void>> deletespit(@PathVariable String id) {
        System.out.println(id);
        return
        //如果操作数据，并返回一个mono，这时候用flatmap，如果不操作数据，只是转换数据，使用map
        spitDao.findById(id).flatMap(Spit -> spitDao.delete(Spit).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    //改数据
    @PutMapping(value = "/{id}")
    public Mono<ResponseEntity<Spit>> updateSpit(@PathVariable String id, @RequestBody Spit spit){
        return
                spitDao.findById(id).
                        //操作数据
                        flatMap(sp ->{sp.setContent(spit.getContent());
                         sp.setVisits(spit.getVisits()+1);
                         return spitDao.save(sp);
                        })
                        //转换数据
                 .map(spit1 -> new ResponseEntity<Spit>(spit1,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }
    //根据id查找
    @GetMapping(value = "/{id}")
   public  Mono<ResponseEntity<Spit>> findbyid(@PathVariable String id){

       return  spitDao.findById(id).map(spit -> new ResponseEntity<Spit>(spit,HttpStatus.OK))
               .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/comment/{parentid}/{page}/{size}")
    public Flux<Spit> comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Flux<Spit> byParentid = spitDao.findByParentid(parentid, pageable);
        return byParentid;
    }
    @GetMapping(value = "/stream/{parentid}/{page}/{size}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Spit> comment1(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Flux<Spit> byParentid = spitDao.findByParentid(parentid, pageable);
        return byParentid;
    }
}
