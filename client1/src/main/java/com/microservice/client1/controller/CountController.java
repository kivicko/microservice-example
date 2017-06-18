package com.microservice.client1.controller;

import com.microservice.client1.model.Count;
import com.microservice.client1.service.CountService;
import com.microservice.client1.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kivi on 18.06.2017.
 */
@RestController
@RequestMapping(value = "/kivi-client")
public class CountController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CountService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Count> getAllCount() {

        return service.getAllCounts();
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    public ResponseEntity saveCount(@PathVariable Integer number) {
        if(number == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.WRONG_INPUT);
        }
        return service.save(number);
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCount(@PathVariable Integer number) {
        if(number == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.WRONG_INPUT);
        }
        return service.removeCountByNumber(number);
    }

    @RequestMapping(value = "/biggestCount", method = RequestMethod.GET)
    public ResponseEntity getBiggestCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBiggestCount());
    }

    @RequestMapping(value = "/lowestCount", method = RequestMethod.GET)
    public ResponseEntity getSmallestCount() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getSmallestCount());
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.GET)
    public ResponseEntity getCountByNumber(@PathVariable Integer number) {
        Count countByNumber = service.getCountByNumber(number);
        if(countByNumber == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(countByNumber);
    }

    @ExceptionHandler
    public ResponseEntity handleExceptionForWrongInput(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.WRONG_INPUT);
    }
}