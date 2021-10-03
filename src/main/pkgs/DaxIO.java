/**
 * C-License 1.2 RENEWED
 * @author Jack Meng
 * @since 1.2
 * COPYRIGHT (C) 2021 JACKMENG
 */

package main.pkgs;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class DaxIO {
  private static OutputStreamWriter osw;
  private static Object byteBuffer;

  public DaxIO() {
    osw = new OutputStreamWriter(System.out);
  }

  public static <T> void println(T streamByte) {
    DaxIO.byteBuffer = streamByte;
    try {
      osw.write("" + streamByte);
      new DaxIO();
      DaxIO.println();
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> void print(T streamByte) {
    DaxIO.byteBuffer = streamByte;
    try {
      osw.write("" + streamByte);
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> void print() {
    try {
      osw.write("");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> void flush() {
    try {
      osw.flush();
      osw.notify();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void println() {
    try {
      osw.write("\n");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeBuffer() {
    try {
      osw.write((String) byteBuffer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void close() throws IOException {
    osw.flush();
    osw.close();
    byteBuffer = null;
  }
  
}
