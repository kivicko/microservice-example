package com.microservice.client1.controller;

import com.microservice.client1.model.Count;
import com.microservice.client1.service.CountService;
import com.microservice.client1.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

/**
 * Created by kivi on 18.06.2017.
 */
@RestController
@RequestMapping(value = "/kivi-client")
public class CountController {

    @Autowired
    private CountService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Count> getAllCount(@RequestParam(value = "sorting", defaultValue = "asc") String sorting) {
        Sort.Direction direction = sorting.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        return service.getAllCountsBySorting(direction);
    }

    @RequestMapping(value = "/{number}", method = RequestMethod.POST)
    public ResponseEntity saveCount(@PathVariable Integer number) {

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

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity handleExceptionForWrongInput(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.WRONG_INPUT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleGeneralException3(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorUtil.SOMETHING_WE_DID_NOT_EXPECT);
    }

}