package com.heycar.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.heycar.dto.CarListingCSVDTO;
import com.heycar.exceptions.FileParsingException;

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

    public static List<CarListingCSVDTO> csvToObject( InputStream is ) throws FileParsingException {
        try( BufferedReader fileReader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );

                CSVParser csvParser = new CSVParser( fileReader,
                                                     CSVFormat.DEFAULT.withQuote( '"' ).withFirstRecordAsHeader().withIgnoreHeaderCase()
                                                             .withTrim() ); ) {

            List<CarListingCSVDTO> tutorials = new ArrayList<CarListingCSVDTO>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for( CSVRecord csvRecord : csvRecords ) {
                CarListingCSVDTO tutorial = new CarListingCSVDTO(
                                                                  csvRecord.get( "code" ),
                                                                  csvRecord.get( "make" ),
                                                                  csvRecord.get( "model" ),
                                                                  Integer.parseInt( csvRecord.get( "powerInPs" ) ),
                                                                  Integer.parseInt( csvRecord.get( "year" ) ),
                                                                  csvRecord.get( "color" ),
                                                                  Double.parseDouble( csvRecord.get( "price" ) ) );

                tutorials.add( tutorial );
            }

            return tutorials;
        }
        catch( IOException e ) {
            log.error( "fail to parse CSV file: " + e.getMessage() );
            throw new FileParsingException( "fail to parse CSV file: " + e.getMessage() );
        }
    }

}
