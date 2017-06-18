package com.microservice.client1.controller;

import com.microservice.client1.model.Count;
import com.microservice.client1.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/")
    public List<Count> getAllCount() {
        Count count = new Count();
        count.setId("1");
        count.setNumber(1);
        count.setSaveDate(new Date());
        List<Count> countList = new ArrayList<>();
        countList.add(count);
        return countList;
    }

//    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
//    public ResponseEntity saveCount(@PathVariable Integer number) {
//        if(number == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.WRONG_INPUT);
//        }
//        return service.save(number);
//    }
}