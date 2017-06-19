package com.microservice.client1.controller;

import com.microservice.client1.model.Count;
import com.microservice.client1.service.CountService;
import com.microservice.client1.util.ErrorUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by kivi on 19.06.2017.
 */
public class CountControllerTest {
    @Mock
    CountService countService;

    @InjectMocks
    private CountController countController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(countController).build();
    }

    @Test
    public void testGetRequestOnMain() throws Exception {
        Count count1 = new Count();
        count1.setNumber(1);
        count1.setId("1");

        Count count2 = new Count();
        count2.setId("2");
        count2.setNumber(2);

        when(countService.getAllCountsBySorting(Sort.Direction.ASC)).thenReturn(Arrays.asList(count1, count2));

        mockMvc.perform(get("/kivi-client/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/xml;charset=UTF-8"))
                .andExpect(content().string("<List>" +
                        "<item>" +
                            "<id>1</id>" +
                            "<number>1</number>" +
                            "<saveDate/>" +
                        "</item>" +
                        "<item>" +
                            "<id>2</id>" +
                            "<number>2</number>" +
                            "<saveDate/>" +
                        "</item>" +
                        "</List>"));
    }

    @Test
    public void testSaveCount() throws Exception{
        Integer sampleNumber = 12;
        when(countService.save(sampleNumber)).thenReturn(ResponseEntity.ok(ErrorUtil.SUCCESS + "\n" + sampleNumber));


        MvcResult mvcResult = mockMvc.perform(post("/kivi-client/" + sampleNumber))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(),ErrorUtil.SUCCESS + "\n" +sampleNumber);
    }

    @Test
    public void testDeleteWithNullNumber() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/kivi-client/"))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testGetBiggestCount() throws Exception {
        Count biggestCount = new Count();
        biggestCount.setNumber(100);
        when(countService.getBiggestCount()).thenReturn(biggestCount);


        MvcResult mvcResult = mockMvc.perform(get("/kivi-client/biggestCount"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(),"<Count><id/><number>100</number><saveDate/></Count>");
    }

    @Test
    public void testGetLowestCount() throws Exception {
        Count smallestCount = new Count();
        smallestCount.setNumber(1);
        when(countService.getSmallestCount()).thenReturn(smallestCount);


        MvcResult mvcResult = mockMvc.perform(get("/kivi-client/lowestCount"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(),"<Count><id/><number>1</number><saveDate/></Count>");
    }

    @Test
    public void testGetCountByNumberWhenNumberIsNotFound() throws Exception {
        Integer notFoundNumber = 123;

        when(countService.getCountByNumber(notFoundNumber)).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(get("/kivi-client/" + notFoundNumber))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), ErrorUtil.NOT_FOUND);
    }

    @Test
    public void testGetCountByNumber() throws Exception {
        Integer foundNumber = 123;
        Count foundCount = new Count();
        foundCount.setNumber(foundNumber);
        when(countService.getCountByNumber(foundNumber)).thenReturn(foundCount);

        MvcResult mvcResult = mockMvc.perform(get("/kivi-client/" + foundNumber))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), "<Count><id/><number>123</number><saveDate/></Count>");
    }

    @Test
    public void testDeleteCountWhenNumberIsNull() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/kivi-client/"+ null))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), ErrorUtil.WRONG_INPUT);
    }

    @Test
    public void testDeleteCountWithNumber() throws Exception {
        Integer number = 123;
        when(countService.removeCountByNumber(number)).thenReturn(ResponseEntity.ok(ErrorUtil.SUCCESS));

        MvcResult mvcResult = mockMvc.perform(delete("/kivi-client/"+ number))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), ErrorUtil.SUCCESS);
    }

    @Test
    public void testThrowWrongInputError() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/kivi-client/asdasdas"))
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), ErrorUtil.WRONG_INPUT);
    }


}
