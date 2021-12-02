package resource.classworks.l_37;

/**
 * @author Jack Meng
 *         This interface is a file that was pre-created
 *         by the assignment.
 * 
 *         The ArrayList class will implement this interface.
 * 
 * @see ArrayList
 */

public interface List<E> {
  public int size();

  public boolean add(E obj);

  public void add(int index, E obj);

  public E get(int index);

  public E set(int index, E obj);

  public E remove(int index);
}