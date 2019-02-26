package uk.ac.ucl.bag;

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListBag<T extends Comparable> extends AbstractBag<T> {

    private static class Element<E> {   // This would create a node called "element", consisting of 2 values, value and occurrences, and then another called
        private E value;                 // next which will act as the pointer to the next node (element)
        private int occurrences;
        private Element<E> next;         // instance variables

        public Element(E value, int occurrences, Element<E> next) {
            this.value = value;
            this.occurrences = occurrences;
            this.next = next;
        }

        public int getOccurrences() {
            return occurrences;
        }

        public E getValue() {
            return value;
        }

        public Element<E> getNext() {
            return next;
        }
    }

    private int maxSize;
    private Element<T> contents;

    public LinkedListBag() throws BagException {
        this(MAX_SIZE);
    }

    public int size() {
        int counter = 0;
        Element<T> current = this.contents;
        while (current != null) {
            counter++;
            current = current.getNext();
        }
        return counter;
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
        private int index = 0;

        public boolean hasNext() {
            if (index < size()) {
                return true;
            } else {
                return false;
            }
        }

        public T next(){
            index++;
            //Element<T> current = contents;
            contents = contents.getNext();
            return (T) contents;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListBagUniqueIterator();
    }

    private class LinkedListBagIterator implements Iterator<T> {
        private int index = 0;
        private int count = 0;

        private ArrayList<T> getList() // trying to get an array list of everything, need to use a for loop to look at
        {                               // occurrences and add them multiple times (add the value multiple times)
            ArrayList<T> list = new ArrayList<>();
            //Element<T> current = contents;
            while (contents != null) {
                for (index = 0; index < contents.getOccurrences(); index++) {
                    list.add(contents.getValue());
                }
                contents = contents.getNext();
            }
            return list;
        }

        public boolean hasNext() {
            //Element<T> current = contents;
            if (index < size()) {
                if (count < contents.getOccurrences()) return true;
                if ((count == contents.getOccurrences()) && ((index + 1) < size())) return true;
            }
            return false;
        }

        public T next() {
            ArrayList<T> allOccurrences = getList();
            index++;
            return (allOccurrences.get(index));
        }
    }

    public Iterator<T> allOccurrencesIterator() {
        return new LinkedListBagIterator();
    }

    public void add(T value) throws BagException {
        // If I find the value, need to increment the occurrences value
        // If I don't find the value, need to add the value
        if (contents.getValue() != null){
            while (contents.getNext() != null){
                if (contents.getValue() == value) {
                    contents.occurrences++;
                }
                contents = contents.getNext();
            }
        }
        if (size() < maxSize) {
            this.contents.next = new Element<T>(value, 1, null);
        } else {
            throw new BagException("Bag is full");
        }
    }

    public void addWithOccurrences(T value, int occurrences) throws BagException{
        for (int i = 0; i < contents.getOccurrences(); i++) {
            add(value);
        }
    }

    public boolean contains(T value) {
        while (contents.getValue() != value) {
            contents = contents.getNext();
            if (contents.getNext() == null) {
                return false;
            }
        }
        return true;
    }

    public int countOf(T value) {
        int counter = 0;
        if (contains(value)) {
            for (int i = 0; i < size(); i++) {
                if (contents.getValue() == value) {
                    counter++;
                }
            }
            return counter;
        }
        return 0;
    }

    public void remove(T value){
        //Key is found at head
        //Key is found at the middle / last, but not at head
        //Key is not in list
        if (contents.getValue() == value) { //If the key is at the head
            if (contents.getOccurrences() > 1) { //If there is more than 1 occurrence
                contents.occurrences--;         // Subtract 1 occurrence
            } else {
                contents.value = (T) contents.next; //If there isn't, replace the head with the next value
            }
        } else{
            while (contents.getValue() != value) {  // While the key is not at the current node
                Element<T> previous = contents; // Keep track of previous node
                contents = contents.getNext(); //Iterate to next node
                if (contents == value);             //
                    previous.next = contents.next;


            }

        }
    }

    public boolean isEmpty(){
        if (contents.getValue() == null){
            return true;
        }
        return false;
    }
}