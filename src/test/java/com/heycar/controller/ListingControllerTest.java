package com.heycar.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.heycar.service.ListingService;


@RunWith( SpringRunner.class )
@SpringBootTest
public class ListingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ListingService listingService;

    @Test
    public void test() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                                                        "testData.csv",
                                                        "testData.csv",
                                                        "test/csv",
                                                        "<<csv data>>".getBytes( StandardCharsets.UTF_8 ) );

        mockMvc.perform( multipart( "/upload/1" ).file( file ) )
                .andExpect( status().isOk() );
    }

}
