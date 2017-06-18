package com.microservice.client1.service.impl;

import com.microservice.client1.model.Count;
import com.microservice.client1.service.CountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kivi on 18.06.2017.
 */
@Service
public class CountServiceImpl implements CountService {
    @Override
    public ResponseEntity save(Integer count) {
        return null;
    }

    @Override
    public Count getCountByNumber(Integer number) {
        return null;
    }

    @Override
    public Count getBiggestCount() {
        return null;
    }

    @Override
    public Count getSmallestCount() {
        return null;
    }

    @Override
    public boolean removeCountByNumber() {
        return false;
    }

    @Override
    public List<Count> getAllCounts() {
        return null;
    }
}
