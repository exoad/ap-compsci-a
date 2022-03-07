public final class Pair<T, E> {
    public T t;
    public E e;
    public Pair(T t, E e) {
        this.t = t;
        this.e = e;
    }

    public boolean swap() {
        e = (T) t;
        t = (E) e;
    }
}