package com.dave.salinfo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CsvUtils {

  private CsvUtils() {
    throw new IllegalStateException("DO NOT INSTANTIATE!");
  }

  public static CSVParser readCsv(InputStream is) throws IOException {
    BufferedReader fileReader =
        new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

    return CSVParser.parse(
        fileReader, CSVFormat.DEFAULT.withSkipHeaderRecord().withFirstRecordAsHeader());
  }
}
