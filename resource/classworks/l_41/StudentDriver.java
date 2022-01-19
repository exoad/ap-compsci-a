package resource.classworks.l_41;

import static java.lang.Math.*;
import static java.lang.System.*;

public class StudentDriver {

  public static int generateID(int bound) {
    return (int) floor(random() * bound);
  }

  /**
   * <h1>Linear Search</h1>
   * <p>
   * This method searches the array and finds
   * if the parameter name exists within the
   * the array of students.
   * </p>
   * 
   * import static java.lang.Math.*;
   * import static java.lang.System.*;
   * 
   * public class StudentDriver {
   * 
   * public static int generateID(int bound) {
   * return floor(random() * bound);
   * }
   * 
   * /**
   * <h1>Linear Search</h1>
   * <p>
   * This method searches the array and finds
   * if the parameter name exists within the
   * the array of students.
   * </p>
   * 
   * @param students The array of students to search in
   * @param name     The student to search for
   * @return true ? false representing whether it exists or not
   */
  public static boolean linearSearch(Student[] students, Student name) {
    for (Student e : students)
      if (e.compareTo(name) == 0)
        return true;
    return false;
  }

  /**
   * <h1>Binary Search</h1>
   * <p>
   * This method uses a binary search
   * to determine if the name exists in the
   * array of students given
   * </p>
   * 
   * @param students The array of students to search in
   * @param name     The student to search for
   * @return true ? false representing whether it exists or not
   */
  public static boolean binSearch(Student[] students, Student name) {
    int low = 0;
    int high = students.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (students[mid].compareTo(name) == 0)
        return true;
      else if (students[mid].compareTo(name) < 0)
        low = mid + 1;
      else
        high = mid - 1;
    }
    return false;
  }
  
  /**
   * @param list the array of Students to sort
   * @return The sorted Students
   */ 
  public static Student[] selectionSort (Student[] list) {       
    int min;       
    Student temp;        
    for (int index = 0; index < list.length-1; index++)       
    {          
      min = index;          
      for (int scan = index+1; scan < list.length; scan++)             
        if (list[scan].compareTo(list[min]) < 0)                
        min = scan;           
      temp = list[min];          
      list[min] = list[index];          
      list[index] = temp;       
    }
    return list;
  }   

  public static void main(String[] args) {
    Student s1 = new Student("James", 42394);
    Student s2 = new Student("Henry", 44838);
    Student s3 = new Student("John", 43894);
    Student s4 = new Student("Mary", 42394);
    Student s5 = new Student("Bob", 42394);
    Student s6 = new Student("Sally", 42394);
    Student s7 = new Student("Dennis", 42394);
    Student s8 = new Student("Bill", 42394);
    Student[] students = { s1, s2, s3, s4, s5, s6, s7, s8 };
    Student name = new Student("James", 42394);
    students = selectionSort(students);

    System.out.println("Linear Search: " + linearSearch(students, name));
    System.out.println("Binary Search: " + binSearch(students, name));

    System.out.println("Linear Search: " + linearSearch(students, new Student("Bob", 42394)));
    System.out.println("Binary Search: " + binSearch(students, new Student("Bob", 42394)));
  }

}
