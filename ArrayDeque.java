package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private int start;
    private int end;
    private T[] arr;

    @Override
    public void addLast(T item) {
        this.arr[end] = item;
        this.size += 1;
        this.end = next(this.end);
        this.checkSize();
    }

    private int next(int index) {
        if (index == this.arr.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int prev(int index) {
        if (index == 0) {
            return this.arr.length - 1;
        } else {
            return index - 1;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        int index = this.prev(this.end);
        T x = this.arr[index];
        this.arr[index] = null;
        this.end = index;
        size -= 1;
        checkSize();
        return x;
    }

    @Override
    public void addFirst(T item) {
        this.arr[start] = item;
        this.size += 1;
        this.start = this.prev(this.start);
        this.checkSize();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int index = this.next(this.start);
        T x = this.arr[index];
        this.arr[index] = null;
        this.start = index;
        size -= 1;
        checkSize();
        return x;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        int i = this.next(this.start);
        while (i != this.end) {
            System.out.print(this.arr[i] + " ");
            i = this.next(i);
        }
    }

    @Override
    public T get(int index) {
        int potential = this.start + 1 + index;
        if (potential < this.arr.length) {
            return this.arr[potential];
        }
        potential = potential - this.arr.length;
        if (potential < this.end) {
            return this.arr[potential];
        } else {
            return null;
        }
    }

    private void resize(int capacity) {
        T[] narr = (T[]) new Object[capacity];
        int i = next(start);
        int j = 0;
        while (i != prev(end)) {
            narr[j] = arr[i];
            i = this.next(i);
            j++;
        }
        narr[j] = arr[i];
        start = capacity - 1;
        end = size();
        arr = narr;
    }

    private void checkSize() {
        if (this.size() == this.arr.length) {
            this.resize(this.size * 2);
        } else if (this.size() < (this.arr.length / 4) && this.arr.length > 16) {
            this.resize(this.size * 2);
        }
    }

    public ArrayDeque() {
        this.size = 0;
        this.end = 0;
        this.start = 7;
        this.arr = (T[]) new Object[8];
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque) {
            Deque<T> obj = (Deque<T>) o;
            if (obj.size() != this.size()) {
                return false;
            }
            for (int i = 0; i < obj.size(); i++) {
                if (!obj.get(i).equals(this.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class ArrayDequeIterator<Item> implements Iterator<Item> {
        private int index;
        ArrayDequeIterator() {
            index = ArrayDeque.this.next(start);
        }

        @Override
        public boolean hasNext() {
            return !(index == end);
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item returnVal = (Item) get(index);
            index = ArrayDeque.this.next(index);
            return returnVal;
        }
    }
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}
