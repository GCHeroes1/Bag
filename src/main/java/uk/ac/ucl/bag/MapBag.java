package uk.ac.ucl.bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
        for (int i = 0; i < occurrences; i++) {
            add(value);
        }
    }

    public boolean contains(T value) {
        return contents.containsKey(value);
    }

    public int countOf(T value) {
        if (this.contains(value)) {
            return contents.get(value);
        } else {
            return 0;
        }
    }

    public void remove(T value) { // Get the value if it exists, minus 1, unless its 0, then remove it
        if (this.contains(value)) {
            if (contents.get(value) == 0) {
                contents.remove(value);
            } else {
                contents.put(value, contents.get(value) - 1);
            }
        }
    }

    public int size() {
        return contents.size();
    }

    public boolean isEmpty() {
        return contents.size() == 0;
    }

    private class MapBagUniqueIterator implements Iterator<T> {
        private Iterator<T> keyIterate = contents.keySet().iterator();

        public boolean hasNext() {
            return keyIterate.hasNext();
        }

        public T next() {
            return keyIterate.next();
        }
    }

    public Iterator<T> iterator() {
        return new MapBagUniqueIterator();
    }

    private class MapBagIterator implements Iterator<T> {
        private int index = 0;
        private int count = 0;
        private Iterator<T> keyIterate = contents.keySet().iterator();      //Iterator of keySet
        private Set<T> keySet = contents.keySet();                          //A set of all the keys

        private ArrayList<T> allOccurrences() {          //method that gets an ArrayList of occurrences
            ArrayList<T> array = new ArrayList<>();
            for (T key : keySet) {
                for (count = 0; count < contents.get(keyIterate.next()); count++) {
                    array.add(key);
                }
            }
            return array;
        }

        public boolean hasNext() {
            return keyIterate.hasNext();
        }

        public T next() {
            T nextValue = allOccurrences().get(index);
            index++;
            return nextValue;
        }
    }

    public Iterator<T> allOccurrencesIterator() {
        return new MapBagIterator();
    }
}

