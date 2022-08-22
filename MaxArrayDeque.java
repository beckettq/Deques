package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }
    public T max() {
        T max = null;
        for (int i = 0; i < size(); i++) {
            if (max == null || c.compare(max, get(i)) < 0) {
                max = get(i);
            }
        }
        return max;
    }
    public T max(Comparator<T> comp) {
        T max = null;
        for (int i = 0; i < size(); i++) {
            if (max == null || comp.compare(max, get(i)) < 0)  {
                max = get(i);
            }
        }
        return max;
    }
}
