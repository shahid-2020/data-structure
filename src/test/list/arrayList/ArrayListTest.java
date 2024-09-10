package list.arrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ArrayListTest {
    ArrayList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
    }

    @Test
    void size() {
        assertThat(list.size()).isEqualTo(0);

        list.add(1);
        list.add(2);
        assertThat(list.size()).isEqualTo(2);

        list.remove(1);
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void isEmpty() {
        assertThat(list.isEmpty()).isTrue();

        list.add(1);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    void add() {
        list.add(5);
        list.add(10);

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(5);
        assertThat(list.get(1)).isEqualTo(10);
    }

    @Test
    void get() {
        list.add(42);
        list.add(23);

        assertThat(list.get(0)).isEqualTo(42);
        assertThat(list.get(1)).isEqualTo(23);

        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void indexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertThat(list.indexOf(1)).isEqualTo(0);
        assertThat(list.indexOf(3)).isEqualTo(2);
        assertThat(list.indexOf(10)).isEqualTo(-1);
    }

    @Test
    void contains() {
        list.add(4);
        list.add(5);
        list.add(6);

        assertThat(list.contains(5)).isTrue();
        assertThat(list.contains(10)).isFalse();
    }

    @Test
    void remove() {
        list.add(10);
        list.add(20);
        list.add(30);

        Integer removed = list.remove(1);
        assertThat(removed).isEqualTo(20);
        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0)).isEqualTo(10);
        assertThat(list.get(1)).isEqualTo(30);

        assertThatThrownBy(() -> list.remove(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void clear() {
        list.add(100);
        list.add(200);

        list.clear();
        assertThat(list.size()).isEqualTo(0);
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void sort() {
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
        list.add(1);
        list.add(2);

        Iterator<Integer> iterator = list.iterator();

        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.hasNext()).isFalse();

        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}