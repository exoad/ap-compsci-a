package resource.lessons.l_40;

//********************************************************************
//  Contact.java       Author: Lewis/Loftus/Cocking
//
//  Represents a phone contact.
//********************************************************************

public class Contact implements Comparable {
  private String firstName, lastName, phone;

  // -----------------------------------------------------------------
  // Sets up this contact with the specified information.
  // -----------------------------------------------------------------
  public Contact(String first, String last, String telephone) {
    firstName = first;
    lastName = last;
    phone = telephone;
  }

  // -----------------------------------------------------------------
  // Returns a description of this contact as a string.
  // -----------------------------------------------------------------
  public String toString() {
    return lastName + ", " + firstName + "\t" + phone;
  }

  // -----------------------------------------------------------------
  // Uses both last and first names to determine lexical ordering.
  // -----------------------------------------------------------------
  public int compareTo(Object other) {
    int result;

    if (lastName.equals(((Contact) other).lastName))
      result = firstName.compareTo(((Contact) other).firstName);
    else
      result = lastName.compareTo(((Contact) other).lastName);

    return result;
  }

  // -----------------------------------------------------------------
  // First name accessor.
  // -----------------------------------------------------------------
  public String getFirstName() {
    return firstName;
  }

  // -----------------------------------------------------------------
  // Last name accessor.
  // -----------------------------------------------------------------
  public String getLastName() {
    return lastName;
  }
}