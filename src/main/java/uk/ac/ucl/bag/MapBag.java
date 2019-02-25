package uk.ac.ucl.bag;

import java.util.HashMap;
import java.util.Iterator;

public class MapBag<T extends Comparable> extends AbstractBag<T> {

    private static class Entry<K extends Comparable, V extends Comparable>
    {
        public final K key;           //Act as the key for the hashMap
        public V value;            //Acts the the value
        public Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next)
        {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int maxSize;
    private Entry<K, V> contents;

    public MapBag() throws BagException
    {
        this(MAX_SIZE);
    }

    public MapBag(int maxSize) throws BagException
    {
        if (maxSize > MAX_SIZE)
        {
            throw new BagException("Attempting to create a Bag with size greater than maximum");
        }
        if (maxSize < 1)
        {
            throw new BagException("Attempting to create a Bag with size less than 1");
        }
        this.maxSize = maxSize;
        this.contents = new HashMap<Entry>();
    }

    public void add(T value) throws BagException
    {
        for (ArrayBag.Element element : contents)
        {
            if (element.value.compareTo(value) == 0) // Must use compareTo to compare values.
            {
                element.count++;
                return;
            }
        }
        if (contents.size() < maxSize)
        {
            contents.add(new ArrayBag.Element(1,value));
        }
        else
        {
            throw new BagException("Bag is full");
        }
    }

    public void addWithOccurrences(T value, int occurrences) throws BagException {
    }

    public boolean contains(T value) {
    }

    public int countOf(T value) {
    }

    public void remove(T value) {
    }

    public int size() {
    }

    public boolean isEmpty() {
    }

    private class MapBagUniqueIterator implements Iterator<T> {
    }

    public Iterator<T> allOccurrencesIterator() {
    }

    public Iterator<T> iterator() {
    }
}

