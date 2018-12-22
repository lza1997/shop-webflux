package com.lza.dao;


import com.lza.entity.Spit;


import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SpitDao extends ReactiveMongoRepository<Spit, String> {

    public Flux<Spit> findByParentid(String parentid, Pageable pageable);
}
