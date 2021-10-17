package main.base;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.IOException;

public class Properties {
  private String dir;
  private String subDir;

  public Properties(String dir, String subDir) throws Exception {
    this.dir = dir != null ? dir : null;
    this.subDir = subDir != null ? subDir : null;
  }

  /**
   * @throws Exception this method will be used everything 
   */
  protected void checkNull() throws Exception {
    if ((dir == null || dir == "" || dir.contains(" ") || dir == " ") || (subDir == null || subDir == "" || subDir.contains(" ") || subDir == " "))
      throw new Exception("File directory is invalid because it cannot be unsolved or was not declare properly");
  }

  protected boolean check() throws Exception {
    return (dir == null || dir == "" || dir.contains(" ") || dir == " ") || (subDir == null || subDir == "" || subDir.contains(" ") || subDir == " ");
  }

  public String readLineNumber(int number, Object subDir) throws Exception {
    if (new File(dir + subDir.toString()).exists()) {
      String t = "";
      try (Stream<String> lines = Files.lines(Paths.get(dir + subDir.toString()))) {
        t = lines.skip(number).findFirst().get();
        List<String> f = Files.readAllLines(Paths.get(dir + "saves"), Charset.defaultCharset());
        f.forEach((m) -> System.out.print("\nSuccess on reading: " + m + "\n"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return t;
    }
    throw new Exception("Failed to retrieve the requested data from " + subDir.toString() + " of line at " + number);
  }
}
