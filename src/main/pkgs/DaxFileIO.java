package main.pkgs;

/**
 * C-License 1.2 RENEWED
 * @author Jack Meng
 * @since 1.2
 * COPYRIGHT (C) 2021 JACKMENG
 * File Purpose: File Bundler
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class DaxFileIO {
  private final String fileDir;

  public DaxFileIO(String dir) { fileDir = dir; }

  public String readLineNumber(int number, Object subDir) {
    if (new File(fileDir + subDir.toString()).exists()) {
      String t = "";
      try (Stream<String> lines =
               Files.lines(Paths.get(fileDir + subDir.toString()))) {
        t = lines.skip(number).findFirst().get();
        List<String> f = Files.readAllLines(Paths.get(fileDir + "saves"),
                                            Charset.defaultCharset());
        f.forEach((m) -> System.out.print("\nSuccess on reading: " + m + "\n"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return t;
    }
    return "0";
  }

  public boolean resetData(Object subDir) {
    File fr = new File(fileDir + subDir.toString());
    if (fr.exists())
      fr.delete();
    return true;
  }

  /**
   * @param number is the line number to be read
   * @param typ    the file directory to be read, specifically the directory
   * @return the final value
   */
  public String readLineNumber(int number, File typ) {
    if (typ.exists()) {
      String t = "";
      try (Stream<String> lines = Files.lines(Paths.get(typ.toString()))) {
        t = lines.skip(number).findFirst().get();
        List<String> f = Files.readAllLines(Paths.get(typ.toString()),
                                            Charset.defaultCharset());
        f.forEach((m) -> System.out.print("\nSuccess on reading: " + m + "\n"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return t;
    }
    return "0";
  }
}