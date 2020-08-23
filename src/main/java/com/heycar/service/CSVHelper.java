package com.heycar.service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.heycar.dto.CarListingCSVDTO;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat( MultipartFile file ) {

        if( file.isEmpty() ) {
            return false;
        }

        if( !TYPE.equals( file.getContentType() ) ) {
            return false;
        }

        return true;
    }

    public static List<CarListingCSVDTO> parseCsvFile( MultipartFile file ) {

        CsvToBean<CarListingCSVDTO> csvToBean = null;

        try( Reader reader = new BufferedReader( new InputStreamReader( file.getInputStream() ) ) ) {

            csvToBean = new CsvToBeanBuilder( reader )
                    .withType( CarListingCSVDTO.class )
                    .withIgnoreLeadingWhiteSpace( true )
                    .build();

        }
        catch( Exception ex ) {
            log.error( ex.getMessage() );
        }
        return csvToBean.parse();
    }

}
