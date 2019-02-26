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
        private Set<T> keySet = contents.keySet();                          //A set of all the keys
        private ArrayList<T> keyList = new ArrayList<>(keySet);             //A list of all keys

        public boolean hasNext() {
            //return keyIterate.hasNext();
            if (index < keyList.size()) {
                if (count < contents.get(keyList.get(index))) {
                    return true;
                }
                if ((count == contents.get(keyList.get(index))) && ((index + 1) < keyList.size())) {
                    return true;
                }
            }
            return false;
        }

        public T next() {
            if (count < contents.get(keyList.get(index))) {
                count++;
                return keyList.get(index);
            }
            count = 1;
            index ++;
            return keyList.get(index);
        }
    }

    public Iterator<T> allOccurrencesIterator() {
        return new MapBagIterator();
    }
}

