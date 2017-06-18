package com.microservice.client1.service.impl;

import com.microservice.client1.model.Count;
import com.microservice.client1.repository.CountRepository;
import com.microservice.client1.service.CountService;
import com.microservice.client1.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        //TODO Burada constructor yapacaktım ancak nested constructor gibi bi hata alındı.
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
        PageRequest request = new PageRequest(0,1,new Sort(Sort.Direction.DESC, "number"));

        return repository.findAllBy(request).getContent().get(0);
    }

    @Override
    public Count getSmallestCount() {
        PageRequest request = new PageRequest(0,1,new Sort(Sort.Direction.ASC, "number"));

        return repository.findAllBy(request).getContent().get(0);
    }

    @Override
    public ResponseEntity removeCountByNumber(Integer number) {
        if(repository.findCountByNumber(number) == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.TRY_WITH_DIFFERENT_COUNT);
        }
        repository.deleteByNumber(number);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ErrorUtil.SUCCESS);
    }

    @Override
    public List<Count> getAllCountsBySorting(Sort.Direction direction) {
        PageRequest request = new PageRequest(0,repository.findAll().size(), new Sort(direction, "number"));

        return repository.findAllBy(request).getContent();
    }

}