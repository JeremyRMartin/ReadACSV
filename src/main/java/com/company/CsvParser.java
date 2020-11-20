package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
  private List fileRows = new ArrayList();

  public CsvParser(String infile) throws IOException, CsvValidationException {
    /** CsvParser - Reads csv Files using OpenCSV
     * On Load, Check if file exists & then load it into fileRows
     * @param infile the file to be opened with path information
     */
    if (checkFile(infile)) {
      readCsv(infile);
    }
  }

  protected void readCsv(String csvinfile) throws IOException, CsvValidationException {
    /** readCsv - Read CSV file and load into our fileRows list
     * @param csvinfile CSV file with path information for loading
     */
    FileInputStream csvStream = new FileInputStream(csvinfile);
    InputStreamReader inputStream = new InputStreamReader(csvStream,
        StandardCharsets.UTF_8);
    CSVReader reader = new CSVReader(inputStream);

    String[] nextLine;
    while((nextLine = reader.readNext()) != null) {
      fileRows.add(nextLine);
    }
    reader.close();
  }

  protected void printCsv() {
    /** printCsv - Print out the Csv */

    for (Object row : fileRows) {
      for (String fields : (String[]) row) {
        System.out.print(fields + ",");
      }
      printCsvLine();
    }
  }

  private void printCsvLine(){
    System.out.println("\b\b\n--------------------------");
  }
  private boolean checkFile(String csvfile) {
    /** checkFile - checks to ensure the file exists
     * @return false on file not found, true on found
     */
    if (!Files.exists(Paths.get(csvfile))) {
      System.out.println("File does not exist.");
      return false;
    }
    return true;
  }
}
