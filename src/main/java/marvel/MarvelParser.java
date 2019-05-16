/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package marvel;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

/** Parser utility to load the Marvel Comics dataset. */
public class MarvelParser {

  /**
   * Reads the Marvel Universe dataset. Each line of the input file contains a character name and a
   * comic book the character appeared in, separated by a tab character
   *
   * @spec.requires filename is a valid file path
   * @throws FileNotFoundException if filename does not exist/cannot be found.
   * @param filename the file that will be read
   * @return An iterator of of the list of HeroModel beans created
   */
  public static Iterator<HeroModel> parseData(String filename) throws FileNotFoundException {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(filename));
      CsvToBean<HeroModel> tsvToBean = new CsvToBeanBuilder(reader)
              .withType(HeroModel.class)
              .withIgnoreLeadingWhiteSpace(true)
              .withSeparator('\t')
              .build();

      return tsvToBean.iterator();

    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
      System.out.println(filename + ": file not found");
      System.exit(1);
    }
    catch(IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    return null;
  }
}
