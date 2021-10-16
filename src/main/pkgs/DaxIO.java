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
  private final OutputStreamWriter osw;
  private static Object byteBuffer;

  public DaxIO() {
    osw = new OutputStreamWriter(System.out);
  }

  public <T> void println(T streamByte) {
    DaxIO.byteBuffer = streamByte;
    try {
      osw.write((Object) streamByte + "\n");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public <T> void print(T streamByte) {
    DaxIO.byteBuffer = streamByte;
    try {
      osw.write((Object) streamByte + "");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public <T> void print() {
    try {
      osw.write("");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public <T> void flush() {
    try {
      osw.flush();
      osw.notify();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void println() {
    try {
      osw.write("\n");
      osw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeBuffer() {
    try {
      osw.write((Object) byteBuffer + " \n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void close() throws IOException {
    osw.flush();
    osw.close();
    byteBuffer = null;
  }
  
}
