package com.microservice.client1.repository;

import com.microservice.client1.model.Count;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by kivi on 18.06.2017.
 */
@Repository
public interface CountRepository extends MongoRepository<Count, String> {
    Count findCountByNumber(Integer number);

    @Query()
    Page<Count> findAllBy(Pageable request);

    Integer deleteByNumber(Integer number);
}