package uk.ac.ucl.bag;

import java.util.Iterator;

public class LinkedListBag<T extends Comparable> extends AbstractBag<T> {

    private static class Element<E> {   // This would create a node called "element", consisting of 2 values, value and occurrences, and then another called
        public E value;                 // next which will act as the pointer to the next node
        public int occurrences;
        public Element<E> next;

        public Element(E value, int occurrences, Element<E> next) {
            this.value = value;
            this.occurrences = occurrences;
            this.next = next;
        }
    }

    private int maxSize;
    private Element<T> contents;

    public LinkedListBag() throws BagException {
        this(MAX_SIZE);
    }

    public LinkedListBag(int maxSize) throws BagException{
        if (maxSize > MAX_SIZE) {
            throw new BagException("Attempting to create a Bag with size greater than maximum");
        }
        if (maxSize < 1) {
            throw new BagException("Attempting to create a Bag with size less than 1");
        }
        this.maxSize = maxSize;
        this.contents = new Element<T>(null, 0, null);
    }

    private class LinkedListBagUniqueIterator implements Iterator<T>{

    }

    public Iterator<T> iterator() {
        return new LinkedListBagUniqueIterator();
    }

    private class LinkedListBagIterator implements Iterator<T>{

    }

    public Iterator<T> allOccurrencesIterator() {
        return new LinkedListBagIterator();
    }

    @Override
    public void add(T value) throws BagException{
        /*this.contents = new Element<T>((T)"snake",3,null);
        this.contents.next = new Element<T>((T)"cat",2,null);
        this.contents.next.next = new Element<T>((T) "Lol", 2, null);
        this.current = this.contents;
        // Snake
        this.current =  this.current.next;
        // Cat
        */
    }

    public void addWithOccurrences(T value, int occurrences) throws BagException{}

    public boolean contains(T value) {}

    public int countOf(T value){}

    public void remove(T value){}

    public int size(){}

    public boolean isEmpty(){}
}