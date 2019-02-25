package uk.ac.ucl.bag;

import java.util.HashMap;
import java.util.Iterator;

public class MapBag<T extends Comparable> extends AbstractBag<T> {

    private int maxSize;
    private HashMap<T, Integer> contents;

    public MapBag() throws BagException {
        this(MAX_SIZE);
    }

    public MapBag(int maxSize) throws BagException {
        if (maxSize > MAX_SIZE) {
            throw new BagException("Attempting to create a Bag with size greater than maximum");
        }
        if (maxSize < 1) {
            throw new BagException("Attempting to create a Bag with size less than 1");
        }
        this.maxSize = maxSize;
        this.contents = new HashMap<>();
    }

    public void add(T value) throws BagException {
        if (contents.containsKey(value)) {
            contents.put(value, contents.get(value) + 1);
        } else if (contents.size() < maxSize) {
            contents.put(value, 1);
        } else {
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

