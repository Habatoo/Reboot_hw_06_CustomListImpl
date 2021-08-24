package Laptenkov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования public методов класса {@link CustomListImpl<Object>}.
 *
 * @author habatoo
 */
class CustomListImplTest {

    private CustomListImpl<String> customEmptyList;
    private CustomListImpl<String> customNotEmptyList;

    /**
     * Инициализация экземпляров тестируемого класса {@link CustomListImpl}.
     */
    @BeforeEach
    void setUp() {
        customEmptyList = new CustomListImpl<>();

        customNotEmptyList = new CustomListImpl<>();
        customNotEmptyList.addLast("first");
        customNotEmptyList.addLast("second");
        customNotEmptyList.addLast("third");
        customNotEmptyList.addLast(null);
        customNotEmptyList.addLast(null);
        customNotEmptyList.addLast("last");
    }

    /**
     * Очистка экземпляров тестируемого класса {@link CustomListImpl}.
     */
    @AfterEach
    void tearDown() {
        customEmptyList = null;
        customNotEmptyList = null;
    }

    /**
     * Проверяет создание пустого экземпляра {@link CustomListImpl}.
     * Сценарий, при котором конструктор отрабатывает пустую коллекцию, проверяет размер коллекции
     * равный 0 и отображение коллекции.
     */
    @Test
    public void сustomListImpl_Test() {
        customEmptyList = new CustomListImpl<>();
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals(
                "[ ]", customEmptyList.toString());
    }

    /**
     * Проверяет создание не пустого экземпляра {@link CustomListImpl}.
     * Сценарий, при котором конструктор отрабатывает на прием не пустой объект
     * коллекцию типа Collection, проверяет размер коллекции
     * равный 3.
     */
    @Test
    public void сustomListImplCollection_Test() {
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("first");
        linkedHashSet.add("second");
        linkedHashSet.add("third");
        CustomListImpl<String> customEmptyList = new CustomListImpl<>(linkedHashSet);
        Assertions.assertEquals(3, customEmptyList.size());
        Assertions.assertEquals(
                "[ first second third ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#size_Test()}
     * проверяет метод {@link CustomListImpl#size()}.
     * Проверяет размер не пустого экземпляра {@link CustomListImpl}.
     * Сценарий, при котором не пустой экземпляр возвращает величину
     * объекта равную 0.
     */
    @Test
    public void size_Test() {
        customEmptyList = new CustomListImpl<>();
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals(
                "[ ]", customEmptyList.toString());
    }

    /**
     * Метод  {@link CustomListImplTest#isEmptyEmpty_Test()}
     * проверяет метод {@link CustomListImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomListImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    public void isEmptyEmpty_Test() {
        Assertions.assertEquals(true, customEmptyList.isEmpty());
    }

    /**
     * Метод  {@link CustomListImplTest#isEmptyNotEmpty_Test()}
     * проверяет метод {@link CustomListImpl#isEmpty()}.
     * Проверяет на пустоту экземпляр объекта {@link CustomListImpl}.
     * Сценарий, при котором пустой экземпляр возвращает <code>true</code>,
     * не пустой экземпляр возвращает <code>false</code>.
     */
    @Test
    public void isEmptyNotEmpty_Test() {
        Assertions.assertEquals(false, customNotEmptyList.isEmpty());
    }

    /**
     * Метод {@link CustomListImplTest#add_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#add(Object)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Т и возвращает <code>true</code>.
     */
    @Test
    public void add_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals(true, customEmptyList.add("Test"));
        Assertions.assertEquals(1, customEmptyList.size());
    }

    /**
     * Метод {@link CustomListImplTest#addAllObjectSuccess_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(Object[])}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого массива объектов типа Т и возвращает <code>true</code>.
     */
    @Test
    void addAllObjectSuccess_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals(true, customEmptyList.addAll(new String[]{"One", "Two"}));
        Assertions.assertEquals(2, customEmptyList.size());
        Assertions.assertEquals("[ One Two ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#addAllObjectFail_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(Object[])}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * пустого массива объектов типа Т и выбрасывает IllegalArgumentException.
     */
    @Test
    void addAllObjectFail_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        String[] stringArray = null;
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> customEmptyList.addAll(stringArray));
        Assertions.assertEquals(
                "Объект для добавления не может быть null!", throwable.getMessage());
    }


    /**
     * Метод {@link CustomListImplTest#addAllCollectionSuccess_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(Collection)}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа Collection и возвращает <code>true</code>.
     */
    @Test
    void addAllCollectionSuccess_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals(
                "[ ]", customEmptyList.toString());
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("first");
        linkedHashSet.add("second");
        linkedHashSet.add("third");
        Assertions.assertEquals(
                true, customEmptyList.addAll(linkedHashSet));
        Assertions.assertEquals(
                "[ first second third ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#addAllCollectionFail_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(Collection)}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * пустого объекта типа Collection и выбрасывает IllegalArgumentException.
     */
    @Test
    void addAllCollectionFail_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        Set<String> linkedHashSet = null;
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> customEmptyList.addAll(linkedHashSet));
        Assertions.assertEquals(
                "Объект для добавления не может быть null!", throwable.getMessage());
    }


    /**
     * Метод {@link CustomListImplTest#addAllCollectionFail_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(int, Object[])}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * пустого объекта типа T и выбрасывает IllegalArgumentException.
     */
    @Test
    void testAddAllNullObjectFail_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        String[] stringArray = null;
        Throwable throwable = Assertions.assertThrows(
                IllegalArgumentException.class, () -> customNotEmptyList.addAll(1, stringArray));
        Assertions.assertEquals(
                "Объект для добавления не может быть null!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#testAddAllObjectIndexOutFail_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(int, Object[])}.
     * Сценарий, при котором объект не успешно отрабатывает добавление
     * не пустого объекта типа T, за пределы связанного списка
     * и выбрасывает ArrayIndexOutOfBoundsException.
     */
    @Test
    void testAddAllObjectIndexOutFail_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        String[] stringArray = new String[]{"one", "two"};
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyList.addAll(8, stringArray));
        Assertions.assertEquals(
                "Индекс 8 отрицательный или находится за пределами связанного списка.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#testAddAllObjectIndexSuccess_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(int, Object[])}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа T, в пределах связанного списка
     * и возвращает <code>true</code>.
     */
    @Test
    void testAddAllObjectIndexSuccess_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        String[] stringArray = new String[]{"one", "two"};
        Assertions.assertEquals(
                true, customNotEmptyList.addAll(2, stringArray));
        Assertions.assertEquals(
                8, customNotEmptyList.size());
        Assertions.assertEquals(
                "[ first second one two third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#testAddAllObjectIndexSuccess_Test()}
     * Проверяет проверяет метод {@link CustomListImpl#addAll(int, Object[])}.
     * Сценарий, при котором объект успешно отрабатывает добавление
     * не пустого объекта типа T, в пределах связанного списка
     * и возвращает <code>true</code>.
     */
    @Test
    void addFirst_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        customNotEmptyList.addFirst("Test string");
        Assertions.assertEquals(7, customNotEmptyList.size());
        Assertions.assertEquals(
                "[ Test string first second third null null last ]", customNotEmptyList.toString());

        Assertions.assertEquals(0, customEmptyList.size());
        customEmptyList.addFirst("Test string");
        Assertions.assertEquals(1, customEmptyList.size());
        Assertions.assertEquals(
                "[ Test string ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#addLast_Test()}
     * проверяет метод {@link CustomListImpl#addLast(Object)}
     * Сценарий, при котором пустой экземпляр {@link CustomListImpl} возвращает величину
     * объекта равную 0.
     */
    @Test
    public void addLast_Test() {
        Assertions.assertEquals(0, customEmptyList.size());
        customEmptyList.addLast("Test string");
        Assertions.assertEquals(1, customEmptyList.size());
        Assertions.assertEquals(
                "[ Test string ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#addLastNotEmpty_Test()}
     * проверяет метод {@link CustomListImpl#addLast(Object)}
     * Сценарий, при котором не пустой экземпляр {@link CustomListImpl} возвращает величину
     * объекта равную 6, после добавления равную 7.
     */
    @Test
    public void addLastNotEmpty_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        customNotEmptyList.addLast("Test string");
        Assertions.assertEquals(7, customNotEmptyList.size());
        Assertions.assertEquals(
                "[ first second third null null last Test string ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#offerFirst_Test()}
     * проверяет метод {@link CustomListImpl#offerFirst(Object)}
     * Сценарий, при котором не пустой экземпляр {@link CustomListImpl}
     * возвращает <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Test
    void offerFirst_Test() {
        Assertions.assertEquals(true, customNotEmptyList.offerFirst("add"));
        Assertions.assertEquals(true, customEmptyList.offerFirst("add"));
    }

    /**
     * Метод {@link CustomListImplTest#offerLast_Test()}
     * проверяет метод {@link CustomListImpl#offerLast(Object)}
     * Сценарий, при котором добавляется не пустой экземпляр {@link CustomListImpl}
     * возвращает <code>true</code> если добавление прошло успешно,
     * <code>false</code> если добавление не успешно.
     */
    @Test
    void offerLast_Test() {
        Assertions.assertEquals(6, customNotEmptyList.size());
        customNotEmptyList.offerLast("Test string");
        Assertions.assertEquals(7, customNotEmptyList.size());
        Assertions.assertEquals(
                "[ first second third null null last Test string ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#getSuccess_Test()}
     * проверяет метод {@link CustomListImpl#get(int)}
     * Сценарий, при котором объект ищется по индексу в пределах связаного списка.
     * Возвращает объект по индексу - "second".
     */
    @Test
    void getSuccess_Test() {
        Assertions.assertEquals("second", customNotEmptyList.get(1));
        Assertions.assertEquals("first", customNotEmptyList.get(0));
    }

    /**
     * Метод {@link CustomListImplTest#getSuccess_Test()}
     * проверяет метод {@link CustomListImpl#get(int)}
     * Сценарий, при котором объект ищется по индексу за пределами связаного списка.
     * Выбрасывает исключение ArrayIndexOutOfBoundsException.
     */
    @Test
    void getFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyList.get(8));
        Assertions.assertEquals(
                "Индекс 8 отрицательный или находится за пределами связанного списка.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#getFirst_Test()}
     * проверяет метод {@link CustomListImpl#getFirst()}
     * Сценарий, при котором возвращается не пустой экземпляр возвращает значение
     * объекта равного "first".
     */
    @Test
    public void getFirst_Test() {
        Assertions.assertEquals("first", customNotEmptyList.getFirst());
    }

    /**
     * Метод {@link CustomListImplTest#getFirstNull_Test()}
     * проверяет метод {@link CustomListImpl#getFirst()}
     * Сценарий, при котором идет обращение к пустому экземпляру выбрасывает
     * исключение NoSuchElementException.
     */
    @Test
    public void getFirstNull_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyList.getFirst());
        Assertions.assertEquals(
                "Элемент first пустой!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#getLastSuccess_Test()}
     * проверяет метод {@link CustomListImpl#getLast()}
     * Сценарий, при котором идет обращение к не пустому tail
     * возвращает объект.
     * Сценарий, при котором идет обращение к пустому экземпляру выбрасывает
     * исключение NoSuchElementException.
     */
    @Test
    void getLastSuccess_Test() {
        Assertions.assertEquals(
                "last", customNotEmptyList.getLast());
    }

    /**
     * Метод {@link CustomListImplTest#peekFirst_Test()}
     * проверяет метод {@link CustomListImpl#peekFirst()}
     * Сценарий, при котором возвращается не пустой экземпляр возвращает значение
     * объекта равного "first".
     * Сценарий, при котором возвращается пустой экземпляр возвращает значение
     * объекта равного null.
     */
    @Test
    void peekFirst_Test() {
        Assertions.assertEquals(
                "first", customNotEmptyList.peekFirst());
        Assertions.assertEquals(
                null, customEmptyList.peekFirst());
    }

    /**
     * Метод {@link CustomListImplTest#peekLast_Test()}
     * проверяет метод {@link CustomListImpl#peekLast()}
     * Сценарий, при котором возвращается не пустой экземпляр возвращает значение
     * объекта равного "last".
     */
    @Test
    void peekLast_Test() {
        Assertions.assertEquals(
                "last", customNotEmptyList.peekLast());
        Assertions.assertEquals(
                null, customEmptyList.peekLast());
    }

    /**
     * Метод {@link CustomListImplTest#setSuccess_Test()}
     * проверяет метод {@link CustomListImpl#set(int, Object)}
     * Сценарий, при котором возвращается старый объект
     * равный "second".
     */
    @Test
    void setSuccess_Test() {
        Assertions.assertEquals(
                "second", customNotEmptyList.set(1, "new"));
        Assertions.assertEquals(
                "[ first new third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#setFail_Test()}
     * проверяет метод {@link CustomListImpl#set(int, Object)}
     * Сценарий, при котором выбрасывается исключение ArrayIndexOutOfBoundsException
     * при обращении метода по индеску за пределами связанного списка.
     */
    @Test
    void setFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyList.set(10, "none"));
        Assertions.assertEquals(
                "Индекс 10 отрицательный или находится за пределами связанного списка.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#removeFail_Test()}
     * проверяет метод {@link CustomListImpl#remove(int)}.
     * Сценарий, при котором идет обращение за пределами связанного списка
     * выбрапсывает исключение ArrayIndexOutOfBoundsException.
     */
    @Test
    void removeFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                ArrayIndexOutOfBoundsException.class, () -> customNotEmptyList.remove(10));
        Assertions.assertEquals(
                "Индекс 10 отрицательный или находится за пределами связанного списка.", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#removeSuccess_Test()}
     * проверяет метод {@link CustomListImpl#remove(int)}.
     * Сценарий, при котором идет обращение в пределах связанного списка
     * удаляет объект по переданному индексу.
     */
    @Test
    void removeSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        customNotEmptyList.remove(1);
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ first third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#removeValueFail_Test()}
     * проверяет метод {@link CustomListImpl#remove(Object)}.
     * Сценарий, при котором идет обращение на удаление не существующего
     * объекта связанного списка возвращает <code>false</code>.
     */
    @Test
    void removeValueFail_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals(false, customNotEmptyList.remove("none"));
        Assertions.assertEquals(6, customNotEmptyList.size());
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#removeValueFail_Test()}
     * проверяет метод {@link CustomListImpl#remove(Object)}.
     * Сценарий, при котором идет обращение на удаление существующего
     * объекта связанного списка возвращает <code>true</code>.
     */
    @Test
    void removeValueSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals(true, customNotEmptyList.remove(null));
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ second third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#pollFirstSuccess_Test()}
     * проверяет метод {@link CustomListImpl#pollFirst()}.
     * Сценарий, при котором идет обращение на удаление существующего
     * объекта first связанного списка возвращает удаленный объект.
     */
    @Test
    void pollFirstSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("first", customNotEmptyList.pollFirst());
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ second third null null last ]", customNotEmptyList.toString());

        Assertions.assertEquals("second", customNotEmptyList.pollFirst());
        Assertions.assertEquals(4, customNotEmptyList.size());
        Assertions.assertEquals("[ third null null last ]", customNotEmptyList.toString());

        Assertions.assertEquals("[ ]", customEmptyList.toString());
        customEmptyList.addFirst("last");
        customEmptyList.addFirst("first");
        Assertions.assertEquals("first", customEmptyList.pollFirst());
        Assertions.assertEquals(1, customEmptyList.size());
        Assertions.assertEquals("[ last ]", customEmptyList.toString());

        Assertions.assertEquals("last", customEmptyList.pollFirst());
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals("[ ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#pollFirstFail_Test()}
     * проверяет метод {@link CustomListImpl#pollFirst()}.
     * Сценарий, при котором идет обращение на удаление не существующего
     * объекта first связанного списка возвращает null.
     */
    @Test
    void pollFirstFail_Test() {
        Assertions.assertEquals("[ ]", customEmptyList.toString());
        Assertions.assertEquals(null, customEmptyList.pollFirst());
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals("[ ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#pollLastSuccess_Test()}
     * проверяет метод {@link CustomListImpl#pollLast()}.
     * Сценарий, при котором идет обращение на удаление существующего
     * объекта last связанного списка возвращает удаленный объект.
     */
    @Test
    void pollLastSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("last", customNotEmptyList.pollLast());
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ first second third null null ]", customNotEmptyList.toString());
        Assertions.assertEquals(null, customNotEmptyList.pollLast());
        Assertions.assertEquals(4, customNotEmptyList.size());

        Assertions.assertEquals("[ ]", customEmptyList.toString());
        customEmptyList.addLast("first");
        customEmptyList.addLast("last");
        Assertions.assertEquals("last", customEmptyList.pollLast());
        Assertions.assertEquals(1, customEmptyList.size());
        Assertions.assertEquals("[ first ]", customEmptyList.toString());

        Assertions.assertEquals("first", customEmptyList.pollLast());
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals("[ ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#pollLastFail_Test()}
     * проверяет метод {@link CustomListImpl#pollLast()}.
     * Сценарий, при котором идет обращение на удаление не существующего
     * объекта last связанного списка возвращает null.
     */
    @Test
    void pollLastFail_Test() {
        Assertions.assertEquals("[ ]", customEmptyList.toString());
        Assertions.assertEquals(null, customEmptyList.pollLast());
        Assertions.assertEquals(0, customEmptyList.size());
        Assertions.assertEquals("[ ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#removeFirstFail_Test()}
     * проверяет метод {@link CustomListImpl#removeFirst()}.
     * Сценарий, при котором идет обращение к элементу связанного списка
     * first равного null выбрасывает исключение NoSuchElementException.
     */
    @Test
    void removeFirstFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyList.removeFirst());
        Assertions.assertEquals(
                "Элемент first пустой!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#removeFirstSuccess_Test()}
     * проверяет метод {@link CustomListImpl#removeFirst()}.
     * Сценарий, при котором идет обращение к элементу связанного списка
     * first не равного null возвращает элемент first.
     */
    @Test
    void removeFirstSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("first", customNotEmptyList.removeFirst());
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("second", customNotEmptyList.removeFirst());
        Assertions.assertEquals(4, customNotEmptyList.size());
        Assertions.assertEquals("[ third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("third", customNotEmptyList.removeFirst());
        Assertions.assertEquals(3, customNotEmptyList.size());
        Assertions.assertEquals("[ null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals(null, customNotEmptyList.removeFirst());
        Assertions.assertEquals(2, customNotEmptyList.size());
        Assertions.assertEquals("[ null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#removeLastFail_Test()}
     * проверяет метод {@link CustomListImpl#removeLast()}.
     * Сценарий, при котором идет обращение к элементу связанного списка
     * last равного null выбрасывает исключение NoSuchElementException.
     */
    @Test
    void removeLastFail_Test() {
        Throwable throwable = Assertions.assertThrows(
                NoSuchElementException.class, () -> customEmptyList.removeLast());
        Assertions.assertEquals(
                "Элемент last пустой!", throwable.getMessage());
    }

    /**
     * Метод {@link CustomListImplTest#removeLastSuccess_Test()}
     * проверяет метод {@link CustomListImpl#removeLast()}.
     * Сценарий, при котором идет обращение к элементу связанного списка
     * last не равного null возвращает элемент last.
     */
    @Test
    void removeLastSuccess_Test() {
        Assertions.assertEquals("[ first second third null null last ]", customNotEmptyList.toString());
        Assertions.assertEquals("last", customNotEmptyList.removeLast());
        Assertions.assertEquals(5, customNotEmptyList.size());
        Assertions.assertEquals("[ first second third null null ]", customNotEmptyList.toString());
        Assertions.assertEquals(null, customNotEmptyList.removeLast());
        Assertions.assertEquals(4, customNotEmptyList.size());
        Assertions.assertEquals("[ first second third null ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#contains_Test()}
     * проверяет метод {@link CustomListImpl#contains(Object)} наличие объекта в
     * связанном списке.
     * Сценарий, при котором ищется объект имеющийся в связанном списке
     * <code>true</code> если объект присутсвует,
     * <code>false</code> если объект не присутсвует.
     */
    @Test
    void contains_Test() {
        Assertions.assertEquals(
                true, customNotEmptyList.contains("last"));
        Assertions.assertEquals(
                true, customNotEmptyList.contains(null));
        Assertions.assertEquals(
                false, customNotEmptyList.contains("null"));
    }

    /**
     * Метод {@link CustomListImplTest#indexOfSuccess_Test()}
     * проверяет метод {@link CustomListImpl#indexOf(Object)} наличие объекта в
     * связанном списке.
     * Сценарий, при котором ищется объект имеющийся в связанном списке
     * возвращается индекс объекта равный 6.
     */
    @Test
    void indexOfSuccess_Test() {
        Assertions.assertEquals(
                5, customNotEmptyList.indexOf("last"));
        Assertions.assertEquals(
                3, customNotEmptyList.indexOf(null));
    }

    /**
     * Метод {@link CustomListImplTest#reverse_Test()}
     * проверяет метод {@link CustomListImpl#reverse()}
     * разворот последовательности объектов экземпляр объекта {@link CustomListImpl}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк в обратном порядке.
     */
    @Test
    void reverse_Test() {
        customNotEmptyList.reverse();
        Assertions.assertEquals(
                "[ last null null third second first ]", customNotEmptyList.toString());
        customEmptyList.reverse();
        Assertions.assertEquals(
                "[ ]", customEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImpl#toString()}
     * Проверяет отображение экземпляр объекта {@link CustomListImpl}.
     * Сценарий, при котором не пустой экземпляр проверяется на отображение
     * тестовых строк.
     */
    @Test
    public void toString_Test() {
        Assertions.assertEquals(
                "[ first second third null null last ]", customNotEmptyList.toString());
    }

    /**
     * Метод {@link CustomListImplTest#toArray_Test} проверяет создание копии связанного списка.
     * Сценарий проверяет идентичность созданной копии и исходного связанного списка по элементно.
     */
    @Test
    void toArray_Test() {
        Object[] copy = customNotEmptyList.toArray();
        for (int i = 0; i < copy.length; i++) {
            Assertions.assertEquals(customNotEmptyList.get(i), (String) copy[i]);
        }
    }

}