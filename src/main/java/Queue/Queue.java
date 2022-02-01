package Queue;

interface Queue<E> extends Iterable<E> {

    //     Получаем кол-во значений в очереди.
    int size();

    //     Добавляем значение в конец очереди.
    void add(E value);

    //    Добавляет значение в самое начало очереди.
    boolean offerFirst(E value);

    //    Добавляет значение в конец очереди.
    boolean offerLast(E value);

    //   Возвращает с удалением значение по индексу.
    void remove(int index);

    //      Возвращаем без удаления первый елемент очереди.
//      Если очередь пуста, генерирует исключение NoSuchElementException.
    E get();

    //    Возвращает первый элемент в очереди.
    E peek();

    //    Возвращает первый элемент в очереди и удаляет его из коллекции.
    E pollFirst();

    //     Возвращает с удалением последнее значение очереди.
    E pollLast();

    //     Очищает коллекциюи
    void clear();

    Object[] toArray();

}
