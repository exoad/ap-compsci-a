package resource.lessons.l_40;

//********************************************************************
//  PhoneList.java       Author: Lewis/Loftus/Cocking
//
//  Driver for testing an object sort.
//********************************************************************

public class PhoneList {
  // -----------------------------------------------------------------
  // Creates an array of Contact objects, sorts them, then prints
  // them.
  // -----------------------------------------------------------------
  public static void main(String[] args) {
    Contact[] friends = new Contact[8];

    friends[0] = new Contact("John", "Smith", "610-555-7344");
    friends[1] = new Contact("Sarah", "Barnes", "215-555-3427");
    friends[2] = new Contact("Mark", "Riley", "733-555-2969");
    friends[3] = new Contact("Laura", "Getz", "663-555-3984");
    friends[4] = new Contact("Larry", "Smith", "464-555-3489");
    friends[5] = new Contact("Frank", "Phelps", "322-555-2284");
    friends[6] = new Contact("Mario", "Guzman", "804-555-9066");
    friends[7] = new Contact("Marsha", "Grant", "243-555-2837");

    Sorting.insertionSort(friends);

    for (int index = 0; index < friends.length; index++)
      System.out.println(friends[index]);

  }
}
