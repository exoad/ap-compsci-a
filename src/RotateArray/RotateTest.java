package src.RotateArray;

/**
 * Tests for class RotateArray.
 * 
 * All tests in the folder "test" are executed 
 * when the "Test" action is invoked.
 * 
 */

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class RotateTest {

  @Test
  public final void testRotate90() {

    // we define some test input and what result we would expect
    int[][] testInput = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] expectedResult = { { 4, 1, 4, 1 }, { 2, 3, 3, 3 }, { 5, 5, 1, 4 }, { 0, 2, 8, 9 } };
    // int[] testInput = new int[] { 1, 2, 3, 4, 5 };
    // int expectedResult = 5;

    // Call the rotateArray function
    // with test input
    int[][] actualResult = RotateArray.rotateArray(testInput, 90);

    // the actualResult value should be the same as the expectedResult value
    assertTrue("Test input with rotation of 90", Arrays.deepEquals(actualResult, expectedResult));
  }

  @Test
  public final void testRotate180() {

    // we define some test input and what result we would expect
    int[][] testInput = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] expectedResult = { { 0, 5, 2, 4 }, { 2, 5, 3, 1 }, { 8, 1, 3, 4 }, { 9, 4, 3, 1 } };
    // int[] testInput = new int[] { 1, 2, 3, 4, 5 };
    // int expectedResult = 5;

    // Call the rotateArray function
    // with test input
    int[][] actualResult = RotateArray.rotateArray(testInput, 180);

    // the actualResult value should be the same as the expectedResult value
    assertTrue("Test input with rotation of 180", Arrays.deepEquals(actualResult, expectedResult));
  }

  @Test
  public final void testRotate270() {

    // we define some test input and what result we would expect
    int[][] testInput = { { 1, 3, 4, 9 }, { 4, 3, 1, 8 }, { 1, 3, 5, 2 }, { 4, 2, 5, 0 } };
    int[][] expectedResult = { { 9, 8, 2, 0 }, { 4, 1, 5, 5 }, { 3, 3, 3, 2 }, { 1, 4, 1, 4 } };
    // int[] testInput = new int[] { 1, 2, 3, 4, 5 };
    // int expectedResult = 5;

    // Call the rotateArray function
    // with test input
    int[][] actualResult = RotateArray.rotateArray(testInput, 270);

    // the actualResult value should be the same as the expectedResult value
    assertTrue("Test input with rotation of 270", Arrays.deepEquals(actualResult, expectedResult));
  }

}
