/**
 * C-License 1.2 RENEWED
 * @author Jack Meng
 * @since 1.2
 * COPYRIGHT (C) 2021 JACKMENG
 * File Purpose: ENUM to handle all file paths
 */

package main;

public enum Accessor {
  RESOURCE_FOLDER("./resources/..."),
  DIR_FOLDER("./resources/homeworks");
  

  public final String l;

  private Accessor(String l) {
    this.l = l;
  } 


}
