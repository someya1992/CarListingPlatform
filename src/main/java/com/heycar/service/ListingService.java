package com.heycar.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.dto.CarListingCSVDto;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class ListingService {
    
    
    public void parseCsvFile(MultipartFile file)
    {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            // create csv bean reader
            CsvToBean<CarListingCSVDto> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(CarListingCSVDto.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
            List<CarListingCSVDto> users = csvToBean.parse();

            // TODO: save users in DB?

          

        } catch (Exception ex) {
            
        }
    }

}
