package resource.classworks.nov19;

public class StudentDriver {
  public static void main(String[] args) {
    Student s1 = new Student("James", 42394);
    Student s2 = new Student("Henry", 44838);
    System.out.println(s1.compareTo(s2));
  }
}