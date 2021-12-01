package resource.classworks.l_37;

public interface List<E> {
  public int size();
  public boolean add(E obj);
  public void add(int index, E obj);
  public E get(int index);
  public E set(int index, E obj);
  public E remove(int index);
}