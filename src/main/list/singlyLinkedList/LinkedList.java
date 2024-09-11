package list.singlyLinkedList;

import list.IList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<T> implements IList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<>(element);

        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if ((element == null && current.data == null) || (current.data.equals(element))) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        T data;

        if (index == 0) {
            data = head.data;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index-1; i++) {
                current = current.next;
            }

            data = current.next.data;
            current.next = current.next.next;
            if (index == size - 1) {
                tail = current;
            }
        }

        size--;
        return data;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void sort(Comparator<? super T> c) {
        if (size <= 1) return;

        head = mergeSort(head, c);

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        tail = current;
    }

    private Node<T> mergeSort(Node<T> head, Comparator<? super T> c) {
        if (head == null || head.next == null) return head;

        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSort(head, c);
        Node<T> right = mergeSort(nextOfMiddle, c);

        return merge(left, right, c);
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return null;
        }

        Node<T> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    private Node<T> merge(Node<T> left, Node<T> right, Comparator<? super T> c) {
        Node<T> result;

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (c.compare(left.data, right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right, c);
        } else {
            result = right;
            result.next = merge(left, right.next, c);
        }

        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }
}
