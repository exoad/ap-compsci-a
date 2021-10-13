public class AccountDriver {
  public static void main(String[] args) {
    Account[] acctList = new Account[6];
    
    //WRITE: Populate the array with 2 of each type of account. For each type, call
    //each constructor once.
    
    printAccounts(acctList);
    System.out.println();
    
    //WRITE: make a couple of deposits to a couple of accounts. Make a couple of
    //withdrawals: some that work, and some that exceed the balance.
    
    printAccounts(acctList);
    System.out.println();
    
    //Take a look at the code below. It will compound the interest on the SavingsAccount
    //objects ONLY. It uses a new operator "instanceof". What do you think it does?
    for (int i = 0; i < acctList.length; i++) {
      if (acctList[i] instanceof SavingsAccount)
        ((SavingsAccount) acctList[i]).compoundInterest();
    }
    
    printAccounts(acctList);
    System.out.println();
  }
  
  //Prints out all the Accounts in the array. Just iterate through the
  //array and print out each Account. Don't overthink this one.
  //However, be attentive to the polymorphism here--each call to
  //System.out.println calls each object's toString method. This
  //will result in different code being run depending on the type of
  //the object being printed.
  public static void printAccounts(Account[] acctArray){
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
