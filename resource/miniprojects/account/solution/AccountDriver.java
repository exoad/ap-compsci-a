package resource.miniprojects.account.solution;


/**
 * @author Jack Meng this class is the driver class and utilizes the Account,
 *         CheckingAccount, & SavingsAccount classes to test their proper
 *         methods
 */

public class AccountDriver {
  public static void main(String[] args) {
    Account[] acctList = new Account[6];

    // WRITE: Populate the array with 2 of each type of account. For each type,
    // call each constructor once.

    // savings account
    acctList[0] = new SavingsAccount("John", 500, 0.03);
    acctList[1] = new SavingsAccount("Harry", 200, 0.06);

    // checkings account
    acctList[2] = new CheckingAccount("Jason", 780, 3);
    acctList[3] = new CheckingAccount("Jerry", 574, 2);

    // account
    acctList[4] = new Account("Alex", 495);
    acctList[5] = new Account("Andrew", 987);

    printAccounts(acctList);
    System.out.println();

    // WRITE: make a couple of deposits to a couple of accounts. Make a couple
    // of withdrawals: some that work, and some that exceed the balance.

    // deposits
    acctList[0].deposit(48.23);
    acctList[1].deposit(5555);
    acctList[2].deposit(4328);
    acctList[4].deposit(0);

    // withdrawals
    acctList[2].withdraw(34893.3);
    acctList[3].withdraw(4.00);

    printAccounts(acctList);

    // Take a look at the code below. It will compound the interest on the
    // SavingsAccount
    // objects ONLY. It uses a new operator "instanceof". What do you think it
    // does?

    /*
     * Answer: The instanceof checks if the mentioned object, in this case:
     * acctList sub i is checking if it is an instance of class SavingsAccount
     *
     * In general it allows for the access of the child class
     *
     * If it is, it would return true running the code of compoundInterest, or
     * using the derived class's methods, but does not follow standard
     * inheritance rules. Else it would ignore
     */
    for (int i = 0; i < acctList.length; i++) {
      if (acctList[i] instanceof SavingsAccount)
        ((SavingsAccount)acctList[i]).compoundInterest();
      else if (acctList[i] instanceof CheckingAccount)
        ((CheckingAccount)acctList[i]).writeCheck(5, "Jeremy");
    }

    printAccounts(acctList);
    System.out.println();
  }

  // Prints out all the Accounts in the array. Just iterate through the
  // array and print out each Account. Don't overthink this one.
  // However, be attentive to the polymorphism here--each call to
  // System.out.println calls each object's toString method. This
  // will result in different code being run depending on the type of
  // the object being printed.
  public static void printAccounts(Account[] acctArray) {
    // loop through the array and print the accounts
    for (Account acct : acctArray)
      System.out.println(acct.toString());
  }
}
