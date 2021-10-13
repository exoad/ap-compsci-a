package resource.miniprojects.account.original;

public class CheckingAccount extends Account {
  //Additional properties/fields:
  private int nextCheckNumber;
  
  //Additional methods:
  
  //Constructs a CheckingAccount with given owner, balance, and starting check number.
  public CheckingAccount(String newOwner, double initBalance, int startingCheckNumber) {
    super(newOwner, initBalance);
    nextCheckNumber = startingCheckNumber;
  }
  
  //Constructs a CheckingAccount with given owner and starting check number, with an
  //initial balance of zero, by calling THIS class' other constructor method.
  public CheckingAccount(String newOwner, int startingCheckNumber) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
  //Additional accessor
  public int getNextCheckNumber() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
  //Writes a check for the given amount to the given recipient.
  //Always increment nextCheckNumber.
  //If the amount exceeds the balance, leave the balance alone, print a message
  //that the check has bounced, and return false.
  //Otherwise, decrease the balance by the amount of the check, print a "check"
  //to the screen showing the check number, recipient, and amount of the check (this is in
  //lieu of printing an actual check), and return true.
  public boolean writeCheck(double amount, String recipient) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
  
  //Returns a String representation of the Checking Account. This can call the 
  //Account's toString method (by using super.toString()) but must add the
  //next check number to the returned value.
  public String toString() {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
