package main.base;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import main.bin.StorageVar;

public class Properties {
  private final String dir;
  private final String subDir;
  protected final StorageVar pb = new StorageVar();

  public Properties(String dir, String subDir) throws Exception {
    if(check())
      throw new Exception(new NullPointerException());
    else {
      this.dir = dir;
      this.subDir = subDir;
    }

  }

  /**
   * @throws Exception this method will be used everything 
   */
  protected void checkNull() throws Exception {
    if (dir == null || dir.equals("") || dir.contains(" ") || subDir == null || subDir.equals("") || subDir.contains(" "))
      throw new Exception("File directory is invalid because it cannot be unsolved or was not declare properly");
  }

  protected boolean check() throws Exception {
    return dir == null || dir.equals("") || dir.contains(" ") || subDir == null || subDir.equals("") || subDir.contains(" ");
  }

  public String readLineNumber(int number, Object subDir) throws Exception {
    if (new File(dir + subDir.toString()).exists()) {
      String t = "";
      try (Stream<String> lines = Files.lines(Paths.get(dir + subDir.toString()))) {
        t = lines.skip(number).findFirst().get();
        List<String> f = Files.readAllLines(Paths.get(dir + subDir.toString()), Charset.defaultCharset());
        f.forEach((m) -> System.out.print("\nSuccess on reading: " + m + "\n"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return t;
    }
    throw new Exception("Failed to retrieve the requested data from " + subDir.toString() + " of line at " + number);
  }
}
