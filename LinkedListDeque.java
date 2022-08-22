package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class DList {
        private T item;
        private DList prev;
        private DList next;
        DList(DList prev, T item, DList next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private int size = 0;
    private DList sentinel;
    @Override
    public void addFirst(T item) {
        DList a = new DList(sentinel, item, sentinel.next);
        this.sentinel.next.prev = a;
        this.sentinel.next = a;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        DList a = new DList(sentinel.prev, item, sentinel);
        this.sentinel.prev.next = a;
        this.sentinel.prev = a;
        this.size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        DList a = this.sentinel.next;
        while (a != this.sentinel) {
            System.out.print(a.item + " ");
            a = a.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T val = this.sentinel.next.item;
        DList a = this.sentinel.next.next;
        this.sentinel.next = a;
        a.prev = this.sentinel;
        this.size -= 1;
        return val;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T val = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size -= 1;
        return val;
    }

    @Override
    public T get(int index) {
        DList init = this.sentinel.next;
        for (int i = 0; i < this.size; i++) {
            if (i == index) {
                return init.item;
            }
            init = init.next;
        }
        return null;
    }
    private T recursiveHelper(DList lst, int index) {
        if (lst == sentinel) {
            return null;
        }
        if (index == 0) {
            return lst.item;
        } else {
            return recursiveHelper(lst.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return recursiveHelper(sentinel.next, index);
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
    public LinkedListDeque() {
        this.sentinel = new DList(null, null,  null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        DList placePointer;
        LinkedListDequeIterator() {
            placePointer = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            if (placePointer == sentinel) { //equals not defined?
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnVal = (T) placePointer.item;
            placePointer = placePointer.next;
            return returnVal;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

}
