package resource.lessons.l_37;

//********************************************************************
//  Nirvana.java       Author: Lewis/Loftus
//
//  Demonstrates the use of a ArrayList object.
//********************************************************************
import java.util.*;

public class Nirvana {
  // -----------------------------------------------------------------
  // Stores and modifies a list of band members.
  // -----------------------------------------------------------------
  public static void main(String[] args) {
    List<String> band = new ArrayList<>();
    band.add("Kurt");
    band.add("Chris");
    band.add("Chad");
    System.out.println(band);
    int location = band.indexOf("Chad");
    band.remove(location);
    System.out.println(band);
    System.out.println("At index 1: " + band.get(1));
    band.add(2, "Dave");
    System.out.println(band);
    System.out.println("Size of the band: " + band.size());
  }
}
