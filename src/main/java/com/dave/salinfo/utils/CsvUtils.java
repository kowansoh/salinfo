package com.dave.salinfo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvUtils {

  private CsvUtils() {
    throw new IllegalStateException("DO NOT INSTANTIATE!");
  }

  public static Iterable<CSVRecord> readCsv(InputStream is, String... headers) {
    try (BufferedReader fileReader =
            new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        CSVParser csvParser =
            new CSVParser(
                fileReader, CSVFormat.DEFAULT.withSkipHeaderRecord().withHeader(headers))) {

      return csvParser.getRecords();

    } catch (IOException e) {
      throw new IllegalArgumentException("fail to parse CSV file : " + e.getMessage());
    }
  }
}
