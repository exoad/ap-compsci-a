public class Runner {
  public static void main(String... args) {
    ClientClass exit = new ClientClass("Do you want to exit? (y/n):");
    do {
    } while(!exit.getOutput().equals("y") || !exit.getOutput().equals("Y"));

  }
}
