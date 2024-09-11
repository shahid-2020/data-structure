package list.singlyLinkedList;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedListTest {

    @Test
    void size() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThat(list.size()).isEqualTo(0);

        list.add(1);
        assertThat(list.size()).isEqualTo(1);

        list.add(2);
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThat(list.isEmpty()).isTrue();

        list.add(1);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void add() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
    }

    @Test
    void get() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void indexOf() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        assertThat(list.indexOf(1)).isEqualTo(0);
        assertThat(list.indexOf(2)).isEqualTo(1);
        assertThat(list.indexOf(3)).isEqualTo(-1);
    }

    @Test
    void contains() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        assertThat(list.contains(1)).isTrue();
        assertThat(list.contains(2)).isTrue();
        assertThat(list.contains(3)).isFalse();
    }

    @Test
    void remove() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.size()).isEqualTo(3);

        assertThat(list.remove(1)).isEqualTo(2);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(3);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(2));
    }

    @Test
    void clear() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.clear();

        assertThat(list.size()).isEqualTo(0);
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void sort() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(3);
        list.add(1);
        list.add(2);

        list.sort(Integer::compareTo);

        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
    }

    @Test
    void iterator() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        Iterator<Integer> iterator = list.iterator();

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void forEach() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        StringBuilder result = new StringBuilder();
        list.forEach(result::append);

        assertThat(result.toString()).isEqualTo("123");
    }
}
