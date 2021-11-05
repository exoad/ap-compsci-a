package resource.miniprojects.account.solution;


/**
 * @author Jack Meng
 * This class is inherited by the classes CheckingAccount & SavingsAccount
 * and is the basis for all of the other types of accounts.
 *
 * Driver Class: AccountDriver
 */

import java.text.*;
import java.util.*;
// Note: the line
//   throw new UnsupportedOperationException("Not yet implemented");
// is a placeholder used so that the method will compile, even if not written.
// If you attempt to call this method, the program will throw an
// exception, generally causing the program to crash.
// When you code the method, DELETE the throw line.

public class Account {
  // Properties/fields:
  private String owner;
  private double balance;

  // Methods:

  // Constructs a new Account with the given owner and balance.
  public Account(String newOwner, double initBalance) {
    owner = newOwner;
    balance = initBalance;
  }

  // Constructs a new Account with a balance of zero. Write this by
  // calling the other constructor method.
  public Account() {
    // call the default constructor
    this("", 0.0);
  }

  // accessors
  public double getBalance() { return balance; }

  public String getOwner() { return owner; }

  // mutators
  public void setOwner(String newOwner) { owner = newOwner; }

  // note: there is no specific mutator method for the balance.

  // Deposits the given amount into the account. You may assume for the
  // moment that the amount is positive.
  public void deposit(double amount) {
    // add the amount to the account
    balance += amount;
  }

  // Withdraws the given amount from the account. If the amount to be
  // withdrawn exceeds the balance of the account, leave the balance
  // unchanged and return false. Otherwise, change the balance to reflect
  // the amount withdrawn and return true.
  public boolean withdraw(double amount) {
    // check if the amount is in range of the balance
    if (amount <= balance) {
      balance -= amount;
      return true;
    }
    return false;
  }

  // Returns a String representation of the account. The representation
  // should include the word "Account", the owner, and the balance. For
  // super-awesome-street-cred, correctly format the balance (in case
  // you've never used money, that would be rounded to the nearest
  // hundredth.)
  public String toString() {
    return "Account\n"
        + "Owner: " + owner + "\nBalance: $" + String.format("%.2f", balance) +
        "\n";
  }
}
