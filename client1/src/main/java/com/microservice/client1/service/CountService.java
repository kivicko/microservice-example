package com.microservice.client1.service;

import com.microservice.client1.model.Count;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by kivi on 18.06.2017.
 */
public interface CountService {
    public ResponseEntity save(Integer count);
    public Count getCountByNumber(Integer number);
    public Count getBiggestCount();
    public Count getSmallestCount();
    public ResponseEntity removeCountByNumber(Integer number);
    public List<Count> getAllCountsBySorting(Sort.Direction direction);
}