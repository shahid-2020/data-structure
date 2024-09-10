package list;

import java.util.Comparator;

public interface IList<T> extends Iterable<T> {
    int size();

    boolean isEmpty();

    boolean add(T element);

    T get(int index);

    int indexOf(T element);

    boolean contains(T element);

    T remove(int index);

    void clear();

    void sort(Comparator<? super T> c);
}
