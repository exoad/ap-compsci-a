//package resource.classworks.l_37;

/**
 * @author Jack Meng
 *         Class: ArrayList
 *         Description: This class is a generic array list class
 *         that can be used to store any type of object and can grow
 *         as needed.
 */

public class ArrayList<E> implements List<E> {
  private Object[] array;
  private int initialCapacity = 10, iterator = 0;

  /**
   * Constructor: {@code ArrayList()}
   * This first constructor creates an array list with a default size
   * of 10
   */
  public ArrayList() {
    assert initialCapacity == 10;
    array = new Object[initialCapacity];
  }

  /**
   * Constructor: {@code ArrayList(int initialCapacity)}
   * 
   * @param initialCapacity the initial capacity of the array list
   *                        This constructor creates an array list with a
   *                        specified initial
   *                        capacity
   */
  public ArrayList(int initialCapacity) {
    this.initialCapacity = initialCapacity;
    array = new Object[] {};
  }

  /**
   * Method: {@code expand()}
   * <p>
   * This method doubles the size of the array list
   * </p>
   * 
   * This is done so by the usage of a temporary array and then
   * copying the contents of the old array into the new array.
   */
  private void expand() {
    Object[] temp = new Object[array.length * 2];
    for (int i = 0; i < array.length; i++) {
      temp[i] = array[i];
    }
    array = temp;
  }

  /**
   * Method: {@code add(E element)}<br<
   * 
   * @param obj the element to be added to the array list
   * 
   *            <p>
   *            This method appends obj to the end of the array
   *            </p>
   */
  @Override
  public boolean add(E obj) {
    if (array.length == 0) {
      array = new Object[] { obj };
      return true;
    }
    if (array.length == iterator)
      expand();
    array[iterator++] = obj;
    return true;
  }

  /**
   * Method: {@code size()}
   * 
   * @return the size of the array list that are FILLED
   */
  @Override
  public int size() {
    return iterator;
  }

  /**
   * Method: {@code add(int index, E obj)}
   * This method adds an element at the position specified by index
   * It does this by shifting all elements after the specified index (insertion)
   * 
   * @param index The index to add the element at
   * @param obj   The element to be added
   */
  @Override
  public void add(int index, E obj) {
    if (index < 0 || index > iterator)
      throw new IndexOutOfBoundsException();
    if (array.length == 0) {
      array = new Object[] { obj };
      return;
    }
    if (array.length == iterator)
      expand();
    for (int i = iterator; i > index; i--) {
      array[i] = array[i - 1];
    }
    array[index] = obj;
    iterator++;
  }

  /**
   * Method: {@code get(int index)}
   * 
   * @param index the index of the element to be returned
   * @return the element at the specified index
   */
  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (index < 0 || index >= iterator)
      throw new IndexOutOfBoundsException();
    return (E) array[index];
  }

  /**
   * Method: {@code set(int index, E obj)}
   * This method replaces the element at the specified index with the specified
   * obj param
   * 
   * @param index the index of the element to be replaced
   * @param obj   the element to replace the old element
   */
  @SuppressWarnings("unchecked")
  @Override
  public E set(int index, E obj) {
    // replace the element at the specified index with the specified obj
    if (index < 0 || index >= iterator)
      throw new IndexOutOfBoundsException();
    E temp = (E) array[index];
    array[index] = obj;
    return temp;
  }

  /**
   * Method: {@code remove(int index)}
   * This method removes the element at the specified index
   * 
   * @param index the index of the element to be removed
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= iterator)
      throw new IndexOutOfBoundsException();
    E temp = get(index);
    for (int i = index; i < iterator - 1; i++) {
      array[i] = array[i + 1];
    }
    iterator--;
    return temp;
  }

  public String toString() {
    String s = "[";
    for (int i = 0; i < iterator; i++)
      s += array[i] + (i != iterator - 1 ? ", " : "");
    return s += "]";
  }
}