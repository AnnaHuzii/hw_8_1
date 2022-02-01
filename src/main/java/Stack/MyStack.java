package Stack;

import java.util.*;

public class MyStack<E> implements Stack<E> {
    private static int DEFAULT_CAPACITY = 64;

    private ArrayList<E> data;

    private int mySize;

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private enum Action {
        GET_AND_REMOVE,
        GET_NO_REMOVE
    }

    public MyStack(final int initialCapacity) {
        data = new ArrayList<>(initialCapacity);
    }

    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    public E push(E item) {
        data.add(item);
        return item;
    }

    @Override
    public E peek() {
        return getTrailingElement(Action.GET_NO_REMOVE);
    }

    @Override
    public E pop() {
        mySize--;
        return getTrailingElement(Action.GET_AND_REMOVE);
    }

    @Override
    public E remove(int index) {
        return data.remove(index);
    }

    @Override
    public boolean empty() {
        return data.isEmpty();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void clear() {
        mySize = 0;
    }
    public E get(int index){
       return data.get(index);
    }

    @Override
    public int search(Object o) {
        int distance = data.lastIndexOf(o);

        if (distance > -1)
            distance = size() - distance;

        return distance;
    }

    @Override
    public String toString() {
        return Arrays.toString(data.toArray());
    }

    private E getTrailingElement(Action behaviourType) {

        if (empty()) {
            throw new NoSuchElementException();
        }
        E item;

        if (behaviourType == Action.GET_NO_REMOVE) {
            item = get(size() - 1);

        } else {
            item = remove(size() - 1);
        }
        return item;
    }

}
