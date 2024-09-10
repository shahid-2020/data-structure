package list.arrayList;

import list.IList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class ArrayList<T> implements IList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int iteratorIndex = -1;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }

    @Override
    public boolean add(T element) {
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public int indexOf(T element) {
        return IntStream.range(0, size)
                .filter(i -> (element == null && elements[i] == null) || (element != null && element.equals(elements[i])))
                .findFirst()
                .orElse(-1);
    }

    @Override
    public boolean contains(T element) {
        return IntStream.range(0, size)
                .anyMatch(i -> (element == null && elements[i] == null) || (elements != null && element.equals(elements[i])));
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T oldValue = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[size--] = null;
        return oldValue;
    }

    @Override
    public void clear() {
        size = 0;
        elements = null;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort(elements, 0, size, c);
    }

    @Override
    public Iterator<T> iterator() {
        iteratorIndex = 0;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iteratorIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return elements[iteratorIndex++];
            }
        };
    }

}
