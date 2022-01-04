package resource.classworks.l_37;
/**
 * @author Jack Meng
 * Class: Tester
 * Description: This class has 3 practice methods to test the functionalities of the ArrayList class.
 * @see ArrayList
 */

public class ArrayListTester {
  /**
   * This method fills the ArrayList with integers of ranges 0, 1, 4, 9, ..., n * n
   * @param list The ArrayList to fill
   * @param n The upper bound of the range
   */
  static void populateWithIntegers(ArrayList<Integer> list, int n) {
    for (int i = 0; i <= n; i++)
      list.add(i * i);
  }

  /**
   * This method calculates the sum of all the elements in the ArrayList
   * @param list The ArrayList to sum
   * @return sum The sum of all the elements in the ArrayList
   */
  static int sum(ArrayList<Integer> list) {
    int sum = 0;
    for (int i = 0; i < list.size(); i++)
      sum += list.get(i);
    return sum;
  }

  /**
   * This method deletes every other element in the ArrayList
   * For example, an array with elements 0,1,2,3,4,8,10
   * Running through this method the elements will become 1,3,8
   * 
   * @param list The ArrayList to delete every other element from
   */
  static void deleteEveryOther(ArrayList<Integer> list) {
    for (int i = list.size() - 1; i >= 0; i--)
      if (i % 2 == 0)
        list.remove(i);

  }

  static void test1() {
    ArrayList<Integer> list = new ArrayList<>();
    populateWithIntegers(list, 10);
    System.out.println("list: " + list.toString());
    deleteEveryOther(list);
    System.out.println("list mod: " + list.toString());
    // -----------------------------------------------//
    ArrayList<Integer> list2 = new ArrayList<>();
    populateWithIntegers(list2, 56);
    System.out.println("list2: " + list2.toString());
    System.out.println("sum: " + sum(list2));
  }

  static void test_static() {
    ArrayList<Integer> alist = new ArrayList<>();
    alist.add(0);
    alist.add(1);
    alist.add(2);
    alist.add(3);
    alist.add(4);
    alist.add(8);
    alist.add(10);
    deleteEveryOther(alist);
    System.out.println("list: " + alist.toString());
  }

  static void test2() {
    ArrayList<Integer> list = new ArrayList<>(50);
    populateWithIntegers(list, 10);
    System.out.println("list size: " + list.size());
  }

  public static void main(String[] args) {
    test_static();
  }
}