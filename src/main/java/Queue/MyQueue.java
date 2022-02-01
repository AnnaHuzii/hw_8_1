package Queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyQueue<E> implements Queue<E> {
    private static final int MAX_LENGTH = 10;
    private Node<E> first;
    private Node<E> last;

    private int mySize;

    public MyQueue() {
        this(MAX_LENGTH);
    }

    public MyQueue(int length) {
        this(length, MAX_LENGTH);
    }

    public MyQueue(int length, float loadFactor) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина инициализации должна быть больше 0");
        }
        if (loadFactor <= 0) {
            throw new IllegalArgumentException("Коэффициент нагрузки должен быть больше 0");
        }
    }

    @Override
    public int size() {
        return this.mySize;
    }

    @Override
    public void add(E value) {
        offerFirst(value);
    }

    @Override
    public E get() {
        Node<E> element = this.first;
        if (element == null) {
            throw new NoSuchElementException();
        }
        return element.value;
    }

      @Override
    public boolean offerFirst(E value) {
        boolean result;
        if (result = value != null && this.mySize < MAX_LENGTH) {
            Node<E> newNode = new Node<>(null, null, value);
            if (this.first != null) {
                newNode.next = this.first;
                this.first = this.first.prev = newNode;
                if (this.last == null) {
                    this.last = first.next;
                }
            } else {
                this.first = newNode;
            }
            this.mySize++;
        }
        return result;
    }

    @Override
    public boolean offerLast(E value) {
        boolean result;
        if (result = value != null && this.mySize < MAX_LENGTH) {
            Node<E> newNode = new Node<>(null, null, value);
            if (this.last != null) {
                newNode.prev = this.last;
                this.last = last.next = newNode;
                this.mySize++;
            } else {
                if (this.first == null) {
                    result = offerFirst(value);
                } else {
                    this.last = this.first.next = newNode;
                    this.last.prev = this.first;
                    this.mySize++;
                }
            }
        }
        return result;
    }
    @Override
    public E peek() {
        Node<E> element = this.last;
        return element != null ? element.value : null;
    }

    @Override
    public E pollFirst() {
        E value = null;
        if (this.first != null) {
            value = first.value;
            this.first = this.first.next;
            if (this.first != null) {
                first.prev = null;
            }
            this.mySize--;
        }
        return value;
    }

    @Override
    public E pollLast() {
        E value = null;
        if (this.last != null) {
            value = this.last.value;
            if (this.last.prev != this.first) {
                this.last = this.last.prev;
                this.last.next = null;
            } else {
                this.last = null;
            }
            this.mySize--;
        } else {
            if (this.first != null) {
                value = pollFirst();
            }
        }
        return value;
    }

    @Override
    public void clear() {
        if (mySize != 0) {
            Node<E> elem = first;
            Node<E> elem2;
            for (int buff = 0; buff < mySize; buff++) {
                elem2 = elem.getNext();
                elem.prev = null;
                elem.next = null;
                elem.value = null;
                elem = elem2;
            }
            mySize = 0;
            first = null;
            last = null;

        } else {
            System.out.println("Нет элементов в коллекции");
        }
    }

    @Override
    public void remove(int index) {

        Node<E> prevNode;
        Node<E> nextNode;
        if (index < mySize && index >= 0) {
            if (index > mySize / 2) {
                if (index != mySize - 1) {
                    Node<E> lastNode = last;
                    for (int buff = mySize; index != (buff - 1); buff--) {
                        lastNode = lastNode.getPrev();
                    }
                    prevNode = lastNode.getPrev();
                    nextNode = lastNode.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    lastNode.allToNull();
                    mySize--;
                } else {
                    prevNode = last.getPrev();
                    last.allToNull();
                    prevNode.setNext(null);
                    last = prevNode;
                    mySize--;
                }

            } else {
                if (index != 0) {
                    Node<E> firstNode = first;
                    for (int buff = 0; index != buff; buff++) {
                        firstNode = firstNode.getNext();
                    }
                    prevNode = firstNode.getPrev();
                    nextNode = firstNode.getNext();
                    prevNode.setNext(nextNode);
                    nextNode.setPrev(prevNode);
                    firstNode.allToNull();
                    mySize--;
                } else {
                    if (index != mySize - 1) {
                        nextNode = first.getNext();
                        nextNode.setPrev(null);
                        first = nextNode;
                        mySize--;
                    } else {
                        first.allToNull();
                        mySize--;
                    }
                }

            }
        } else {

            System.out.println("Вы вышли за границу коллекции");

        }
        System.out.println("Вы удалили " + index + " елемент");
    }

    @Override
    public String toString() {
       return Arrays.toString(toArray());
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.mySize];
        int index = 0;
        for (Node<E> x = this.first; x != null; x = x.next) {

            array[index++] = x.value;
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorDeque<>(this.first);
    }

    private class Node<E> {
        private Node<E> prev;
        private Node<E> next;
        private E value;

        public Node(Node<E> prev, Node<E> next, E value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        //дезинтегратор йопта! Использовать только в особых назначениях!
        private void allToNull() {
            this.value = null;
            this.next = null;
            this.prev = null;
        }
        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }

    }

    private class IteratorDeque<E> implements Iterator<E> {
        private Node<E> first;

        public IteratorDeque(Node<E> first) {
            this.first = first;
        }

        @Override
        public boolean hasNext() {
            return this.first != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = this.first.value;
            this.first = first.next;
            return value;
        }
    }



}


