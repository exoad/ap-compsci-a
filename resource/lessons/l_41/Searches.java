//********************************************************************
// Searches.java Author: Lewis/Loftus
//
// Demonstrates the linear search and binary search algorithms.
//********************************************************************
public class Searches
{
 public static boolean linearSearch (int[] numbers, int key)
 {
 int index = 0;
 for(int index = 0; index < numbers.length; index++)
 {
 if( key == numbers[index] )
 return true;
 }
 return false;
 }
 public static boolean binarySearch (int[] numbers, int key)
 {
 int low = 0, high = (numbers.length - 1), middle = (low + high)
/ 2;
 boolean found = false;
 while ( (numbers[middle] != key) && (low <= high) )
 {
 if (key < numbers[middle])
 high = middle - 1;
 else
 low = middle + 1;
 middle = (low + high) / 2
 }
 if (numbers[middle] == key)
 return true;
 else
 return false;
 }
}
