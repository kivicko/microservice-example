package com.microservice.client1.service.impl;

import com.microservice.client1.model.Count;
import com.microservice.client1.repository.CountRepository;
import com.microservice.client1.service.CountService;
import com.microservice.client1.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kivi on 18.06.2017.
 */
@Service
public class CountServiceImpl implements CountService {
    @Autowired
    CountRepository repository;

    @Override
    public ResponseEntity save(Integer number) {
        if(repository.findCountByNumber(number) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.TRY_WITH_DIFFERENT_COUNT);
        }
        Count count = new Count();
        count.setSaveDate(new Date());
        count.setNumber(number);
        Count savedCount = repository.save(count);
        return ResponseEntity.ok(ErrorUtil.SUCCESS + "\n" + savedCount.toString() );
    }

    @Override
    public Count getCountByNumber(Integer number) {
        return repository.findCountByNumber(number);
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
        return repository.findAll();
    }
}
