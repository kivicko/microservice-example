package com.microservice.client1.service;

import com.microservice.client1.model.Count;
import com.microservice.client1.repository.CountRepository;
import com.microservice.client1.service.impl.CountServiceImpl;
import com.microservice.client1.util.ErrorUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by kivi on 19.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CountServiceTest {
    @Mock
    private CountRepository repository;

    @InjectMocks
    CountServiceImpl countService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(countService).build();
    }

    @Test
    public void testSaveFail() {
        Integer number = 5;

        when(repository.findCountByNumber(number)).thenReturn(new Count());
        ResponseEntity responseEntity = countService.save(number);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(responseEntity.getBody(), ErrorUtil.TRY_WITH_DIFFERENT_COUNT);
    }

    @Test
    public void testSaveSuccess() throws Exception {
        Integer number = 5;
        Count countToSave = new Count();
        countToSave.setNumber(number);

        when(repository.findCountByNumber(number)).thenReturn(null);
        when(repository.save(any(Count.class))).thenReturn(countToSave);

        ResponseEntity responseEntity = countService.save(number);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetCountByNumber() throws Exception {
        Integer number = 123;
        String defaultId = "defaultId";
        Date defaultDate = new Date();
        Count returnCount = new Count();

        returnCount.setId(defaultId);
        returnCount.setNumber(number);
        returnCount.setSaveDate(defaultDate);

        when(repository.findCountByNumber(number)).thenReturn(returnCount);

        assertEquals(countService.getCountByNumber(number).getNumber(), returnCount.getNumber());
        assertEquals(countService.getCountByNumber(number).getId(), returnCount.getId());
        assertEquals(countService.getCountByNumber(number).getSaveDate(), returnCount.getSaveDate());
    }

    @Test
    public void testRemoveCountByNumber() throws Exception {
        Integer number = 123;
        Date defaultDate = new Date();
        String defaultId = "defaultId";

        Count returnCount = new Count();
        returnCount.setNumber(number);
        returnCount.setId(defaultId);
        returnCount.setSaveDate(defaultDate);

        when(repository.findCountByNumber(number)).thenReturn(returnCount);
        when(repository.deleteByNumber(number)).thenReturn(1);

        assertEquals(countService.removeCountByNumber(number).getStatusCode(),HttpStatus.ACCEPTED);
        assertEquals(countService.removeCountByNumber(number).getBody(),ErrorUtil.SUCCESS);
    }

    @Test
    public void testRemoveCountByNumberWhenCountIsNotFound() throws Exception {
        Integer number = 123;

        when(repository.findCountByNumber(number)).thenReturn(null);

        assertEquals(countService.removeCountByNumber(number).getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(countService.removeCountByNumber(number).getBody(), ErrorUtil.TRY_WITH_DIFFERENT_COUNT);
    }

    @Test
    public void testGetAllCountsBySorting() throws Exception {
//        Sort.Direction direction = Sort.Direction.ASC;
//
//
//        List<Count> countList = Collections.singletonList(new Count());
//
//        when(repository.findAll()).thenReturn(countList);
//        when(repository.findAllBy(any(PageRequest.class))).thenReturn()
    }

}
