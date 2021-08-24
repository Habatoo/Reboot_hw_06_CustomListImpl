package Laptenkov;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Класс CustomListImpl<T> представляет двусвязный список, реализует интерфейс CustomList<T>
 * Класс CustomListImpl может хранить объекты любого типа и может динамически расширяться.
 *
 * @param <T>
 */
public class CustomListImpl<T> implements CustomList<T> {

    /**
     * Внутренний статический класс Node для храниения объектов
     * связанного списка.
     *
     * @param <T>
     */
    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(T item) {
            this.item = item;
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    /**
     * Конструктор пустого объекта {@link CustomListImpl}
     * связанного списка.
     */
    public CustomListImpl() {
        first = last = new Node<>(null);
        size = 0;
    }

    /**
     * Конструктор объекта {@link CustomListImpl}
     * на основе объекта типа Collection.
     *
     * @param c объект коллекции который будет использован для создания массива.
     * @throws IllegalArgumentException если в качестве параметра передан null.
     */
    CustomListImpl(Collection<T> c) {
        if (null == c) {
            throw new IllegalArgumentException("Объект коллекции не может быть null!");
        }

        CustomListImpl<T> customList = new CustomListImpl();
        Object[] newData = c.toArray();
        size = newData.length;
        for (int i = 0; i < size; i++) {
            customList.addLast((T) newData[i]);
        }
        first = customList.first;
    }

    /**
     * Метод {@link CustomListImpl#size()} возвращает размер связанного списка
     * объекта {@link CustomListImpl}.
     *
     * @return целое число типа {@link int}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод {@link CustomListImpl#isEmpty()} возвращает булево значение
     * при проверке связанного списка объекта {@link CustomListImpl}.
     *
     * @return <code>true</code> если размер не нулевой,
     * <code>false</code> если размер нулевой.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Метод {@link CustomListImpl#add(T)} возвращает булево значение
     * при добавления объекта типа Т.
     *
     * @param item обект типа Т.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Override
    public boolean add(T item) {
        addLast(item);
        return true;
    }

    /**
     * Метод {@link CustomListImpl#addAll(T[])} возвращает булево значение
     * при добавления объекта связанного списка типа Т.
     *
     * @param items объект типа {@link CustomListImpl<T>}.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     * @throws IllegalArgumentException если переданный объект items равен null
     */
    @Override
    public boolean addAll(T[] items) {
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }

        for (T item : items) {
            addLast(item);
        }
        return true;
    }

    /**
     * Метод {@link CustomListImpl#addAll(Collection)} возвращает булево значение
     * при добавления объекта типа Collection.
     *
     * @param items объект типа {@link Collection}.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     * @throws IllegalArgumentException если переданный объект items равен null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }

        Object[] newData = items.toArray();
        int newSize = size + newData.length;
        for (Object item : newData) {
            addLast((T) item);
        }
        size = newSize;
        return true;
    }

    /**
     * Метод {@link CustomListImpl#addAll(int, Object[])} возвращает булево значение
     * при добавления объекта массива типа T по указанному индексу.
     *
     * @param index - индекс с которого нужно добавить массив.
     * @param items - массив типа T.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     * @throws ArrayIndexOutOfBoundsException если индекс за пределами связанного списка.
     * @throws IllegalArgumentException       если переданный объект null.
     */
    @Override
    public boolean addAll(int index, T[] items) {
        if (null == items) {
            throw new IllegalArgumentException("Объект для добавления не может быть null!");
        }
        rangeCheck(index);

        int newSize = size + items.length;
        Node<T> tmp = first.next;
        CustomListImpl<T> customList = new CustomListImpl();

        for (int i = 0; i < index; i++) {
            customList.add(tmp.item);
            tmp = tmp.next;
        }

        int j = 0; // индекс для вставки items
        for (int i = index; i < index + items.length; i++) {
            customList.add(items[j++]);
        }

        int newAddStart = index + items.length; // индекс начала для вставки оставшегося начального объекта
        int newAddEnd = newSize; // индекс конца для вставки оставшегося начального объекта
        for (int i = newAddStart; i < newAddEnd; i++) {
            customList.add(tmp.item);
            tmp = tmp.next;
        }

        first = customList.first;
        size = newSize;

        return true;
    }

    /**
     * Метод {@link CustomListImpl#addFirst(T)} добавляет
     * объект связанного списка типа Т на позицию связанного списка first.
     *
     * @param item объект типа {@link CustomListImpl<T>}
     */
    @Override
    public void addFirst(T item) {
        final CustomListImpl.Node<T> f = first;
        Node<T> newNode = new Node<T>(item);

        if (f.next == null) {
            newNode.next = null;
        } else {
            newNode.next = first.next;
        }
        first.next = newNode;
        newNode.prev = first;
        size++;

    }

    /**
     * Метод {@link CustomListImpl#addLast(T)} добавляет
     * объект связанного списка типа Т на позицию связанного списка last.
     *
     * @param item объект типа {@link CustomListImpl<T>}.
     */
    @Override
    public void addLast(T item) {
        final CustomListImpl.Node<T> l = last;
        Node<T> newNode = new Node<T>(item);

        if (null == last) {
            first = newNode;
            first.prev = null;
        } else {
            l.next = newNode;
            newNode.next = null;
            newNode.prev = last;
        }
        last = newNode;
        size++;

    }

    /**
     * Метод {@link CustomListImpl#offerFirst(T)} добавляет
     * объект связанного списка типа Т на позицию связанного списка first.
     *
     * @param item объект типа {@link CustomListImpl<T>}.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Override
    public boolean offerFirst(T item) {
        addFirst(item);
        return true;
    }

    /**
     * Метод {@link CustomListImpl#offerLast(T)} добавляет
     * объект связанного списка типа Т на позицию связанного списка last.
     *
     * @param item объект типа {@link CustomListImpl<T>}.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Override
    public boolean offerLast(T item) {
        addLast(item);
        return true;
    }

    /**
     * Метод {@link CustomListImpl#get(int)} возвращает
     * объект связанного списка типа Т.
     *
     * @param index - index связанного списка, который нужно вернуть.
     * @throws ArrayIndexOutOfBoundsException если индекс за пределами связанного списка.
     */
    @Override
    public T get(int index) {
        rangeCheck(index);
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (i == index) {
                break;
            }
            i++;
            tmp = tmp.next;
        }
        return tmp.item;
    }

    /**
     * Метод {@link CustomListImpl#getFirst()} возвращает
     * объект связанного списка типа Т со стороны head.
     *
     * @throws NoSuchElementException если объект head null.
     */
    @Override
    public T getFirst() {
        final CustomListImpl.Node<T> f = first;
        if (first.next == null) {
            throw new NoSuchElementException("Элемент first пустой!");
        }
        return first.next.item;
    }

    /**
     * Метод {@link CustomListImpl#getLast()} возвращает
     * объект связанного списка типа Т со стороны tail.
     *
     * @throws NoSuchElementException если объект tail null.
     */
    @Override
    public T getLast() {
        if (null == last || last == first) {
            throw new NoSuchElementException("Элемент last пустой!");
        }
        return last.item;
    }

    /**
     * Метод {@link CustomListImpl#getFirst()} возвращает
     * объект связанного списка типа Т со стороны head.
     *
     * @return null если объект пустой.
     */
    @Override
    public T peekFirst() {
        if (first.next == null) {
            return null;
        }
        return first.next.item;
    }

    /**
     * Метод {@link CustomListImpl#getFirst()} возвращает
     * объект связанного списка типа Т со стороны tail.
     *
     * @return null если объект пустой.
     */
    @Override
    public T peekLast() {
        if (last == null || last == first) {
            return null;
        }
        return last.item;
    }

    /**
     * Метод {@link CustomListImpl#set(int, Object)} заменяет объект по
     * указанному индексу.
     * Возвращает старый объект связанного списка.
     *
     * @param index индекс для вставки.
     * @param item  объект для вставки.
     * @return старое значение объекта.
     * @throws ArrayIndexOutOfBoundsException индекс для вставки за пределами связанного списка.
     */
    @Override
    public T set(int index, T item) {
        rangeCheck(index);
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (i == index) {
                break;
            }
            i++;
            tmp = tmp.next;
        }
        T oldItem = tmp.item;
        tmp.item = item;
        return oldItem;
    }

    /**
     * Метод {@link CustomListImpl#remove(int)} удаляет объект по
     * указанному индексу.
     *
     * @param index индекс для удаления.
     * @throws ArrayIndexOutOfBoundsException индекс для вставки за пределами связанного списка.
     */
    @Override
    public void remove(int index) {
        rangeCheck(index);
        CustomListImpl<T> customList = new CustomListImpl();
        Node<T> tmp = first.next;

        for (int i = 0; i < size; i++) {
            if (i != index) {
                customList.addLast(tmp.item);
            }
            tmp = tmp.next;
        }
        size--;
        first = customList.first;
    }

    /**
     * Метод {@link CustomListImpl#remove(int)} удаляет объект из связанного
     * списка по указанному объекту. Удаляется первое ывхождение объекта.
     *
     * @param item объект для удаления.
     * @return <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Override
    public boolean remove(T item) {
        CustomListImpl<T> customList = new CustomListImpl();
        Node<T> tmp = first.next;
        boolean result = false;
        int count = size;
        boolean checkRemoveTimes = true;

        if (null != item) {
            for (int i = 0; i < count; i++) {
                if (item.equals(tmp.item) && checkRemoveTimes) {
                    size--;
                    result = true;
                    tmp = tmp.next;
                    checkRemoveTimes = false;
                    continue;
                }
                customList.addLast(tmp.item);
                tmp = tmp.next;
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (null != tmp.item && checkRemoveTimes) {
                    size--;
                    result = true;
                    tmp = tmp.next;
                    checkRemoveTimes = false;
                    continue;
                }
                customList.addLast(tmp.item);
                tmp = tmp.next;
            }
        }

        first = customList.first;
        return result;
    }

    /**
     * Метод {@link CustomListImpl#pollFirst()} удаляет объект first из связанного
     * списка.
     *
     * @return null если объект first пустой, иначе возвращает объект first.
     */
    @Override
    public T pollFirst() {
        Node<T> tmp = first.next;
        if (null == tmp) {
            return null;
        }

        if (null != first.next.next) {
            first.next.next.prev = first;
            first.next = first.next.next;
        } else {
            first.next = null;
        }
        size--;
        return tmp.item;
    }

    /**
     * Метод {@link CustomListImpl#pollFirst()} удаляет объект last из связанного
     * списка.
     *
     * @return null если объект last пустой, иначе возвращает объект last.
     */
    @Override
    public T pollLast() {
        Node<T> tmp = last;
        if (null == last || last == first) {
            return null;
        }

        if (null != last.prev) {
            last.prev.next = null;
            last = last.prev;
        } else {
            first.next = null;
            last = null;
        }
        size--;
        return tmp.item;
    }

    /**
     * Метод {@link CustomListImpl#removeFirst()} удаляет объект first из связанного
     * списка.
     *
     * @return иначе возвращает объект first.
     * @throws NoSuchElementException если объект first пустой.
     */
    @Override
    public T removeFirst() {
        if (first.next == null) {
            throw new NoSuchElementException("Элемент first пустой!");
        }
        return pollFirst();
    }

    /**
     * Метод {@link CustomListImpl#removeLast()} удаляет объект last из связанного
     * списка.
     *
     * @return иначе возвращает объект last.
     * @throws NoSuchElementException если объект last пустой.
     */
    @Override
    public T removeLast() {
        if (last == null || last == first) {
            throw new NoSuchElementException("Элемент last пустой!");
        }
        return pollLast();
    }

    /**
     * Метод {@link CustomListImpl#indexOf(Object)}
     * проверяет наличие объекта в связанном списке {@link CustomListImpl}
     *
     * @param item - объект для поиска.
     * @return <code>true</code> если объект присутсвует,
     * <code>false</code> если объект не присутсвует.
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    /**
     * Метод {@link CustomListImpl#indexOf(Object)}
     * проверяет наличие объекта в связанном списке {@link CustomListImpl}
     *
     * @param item - объект для поиска.
     * @return возвращает индек найденного элемента или
     * -1 если связаннй список не содержит этот элемент.
     */
    @Override
    public int indexOf(T item) {
        int i = 0;
        Node<T> tmp = first.next;

        while (tmp != null) {
            if (tmp.item == item) {
                return i;
            }
            i++;
            tmp = tmp.next;
        }
        return -1;
    }

    /**
     * Метод {@link CustomListImpl#reverse()}
     * отображает объект связанного списка в обратном порядке {@link CustomListImpl}
     */
    @Override
    public void reverse() {
        CustomListImpl<T> customList = new CustomListImpl();
        Node<T> tmp = last;
        for (int i = 0; i < size; i++) {
            customList.addLast(tmp.item);
            tmp = tmp.prev;
        }
        first = customList.first;
    }

    /**
     * Метод {@link CustomListImpl#toString()}
     * возвращает строковое представление связанного списка {@link CustomListImpl}
     *
     * @return объект типа String в формате '[ element1 element2 ... elementN ] или [ ] если массив пустой.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node<T> tmp = first.next;
        sb.append("[");
        while (tmp != null) {

            sb.append(" " + tmp.item);
            tmp = tmp.next;
        }

        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Метод {@link CustomListImpl<T>#toArray()}
     * возвращает копию текущего объекта
     * {@link CustomListImpl<T>}.
     *
     * @return объект типа {@link CustomListImpl<T>}.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];

        Node<T> tmp = first.next;
        for (int i = 0; i < size; i++) {
            newData[i] = tmp.item;
            tmp = tmp.next;
        }
        return newData;
    }

    /**
     * Метод {@link CustomListImpl<T>#rangeCheck(integer)} проверяет,
     * что передаваемое значение index не отрицательно
     * и не выходит за пределы связанного списка.
     *
     * @param index целое число.
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException(
                    "Индекс " + index + " отрицательный или находится за пределами связанного списка.");
    }

}