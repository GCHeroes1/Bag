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
            return occurrences; }
        public E getValue() {
            return value; }
        public Element<E> getNext() {
            return next; }
    }

    private int maxSize;
    private Element<T> head = null;

    public LinkedListBag() throws BagException {
        this(MAX_SIZE);
    }

    public int size() {
        int counter = 0;
        Element<T> current = this.head;
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
    }

    private class LinkedListBagUniqueIterator implements Iterator<T>{
        private int index = 0;
        private Element<T> current = head;

        public boolean hasNext() {
            if (index < size()) {
                index++;
                return true;
            } else {
                return false;
            }
        }

        public T next(){
            T value = current.getValue();
            current = current.getNext();
            return value;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListBagUniqueIterator();
    }

    private class LinkedListBagIterator implements Iterator<T> {
        private int index = 0;
        private int count = 0;

        private ArrayList<T> getList() { // trying to get an array list of everything, need to use a for loop to look at
                                       // occurrences and add them multiple times (add the value multiple times)
            Element<T> current = head;
            ArrayList<T> listOfElements = new ArrayList<>();
            //Element<T> current = contents;
            while (current != null) {
                for (index = 0; index < size(); index++) {
                    listOfElements.add(current.getValue());
                }
                current = current.getNext();
            }
            return listOfElements;
        }

        public boolean hasNext() {
            Element<T> current = head;
            if (index < size()) {
                if (count < current.getOccurrences()) return true;
                if ((count == current.getOccurrences()) && ((index + 1) < size())) return true;
            }
            return false;
        }

        public T next() {
            ArrayList<T> allOccurrences = getList();
            index++;
            return (allOccurrences.get(index));
                // in has next, need a variable called index which tells me which iteration i'm on, (index starts at 0)
                // need a for loop in next which starts with count at 1,  and loops arounduntil count = index
        }
    }

    public Iterator<T> allOccurrencesIterator() {
        return new LinkedListBagIterator();
    }

    public void add(T value) throws BagException {
        // If I find the value, need to increment the occurrences value
        // If I don't find the value, need to add the value
        Element<T> element = new Element<>(value, 1, null);
        Element<T> current = head;

        if(head == null)
        {
            head = element;
        }
        else {
            int size = 1;
            while (current.getNext() != null && current.getValue() != value) {
                current = current.next;
                size++;
            }
            if (current.getValue() == value)
            {
                if (size < maxSize) {
                    int amount = current.occurrences + 1;
                    current.occurrences = amount;
                }
                else {
                    throw new BagException("Bag is full");
                }
            }
            else if (current.getNext() == null)
            {
                if (size < maxSize) {
                    current.next = element;
                }
                else {
                    throw new BagException("Bag is full");
                }
            }
        }
    }

    public void addWithOccurrences(T value, int occurrences) throws BagException{
        for (int i = 0; i < head.getOccurrences(); i++) {
            add(value);
        }
    }

    public boolean contains(T value) {
        Element<T> current = head;
        while (current != null) {
            if (current.value != value) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public int countOf(T value) {
        Element<T> current = head;
        while (current.getValue() != value) {
            current = current.next;
            if ((current.getValue() != value) && (current.getNext() == null)) {
                return 0;
            }
        }
        return current.getOccurrences();
    }

    public void remove(T value) {
        //Key is found at head
        //Key is found at the middle / last, but not at head
        //Key is not in list
        Element<T> current = head;
        if (current.getValue() == value)
        {                                                       //If the key is at the head
            if (current.getOccurrences() > 1)
            {                                                   //If there is more than 1 occurrence
                current.occurrences--;                             // Subtract 1 occurrence
            }
            else
            {
                current.value = (T) current.next;                //If there isn't, replace the head with the next value
            }
        }
        else {                                                       // if the key isnt at the head
            while (current.getValue() != value) {                                                 // While the key is not at the current node
                Element<T> previous = current;                   // Keep track of previous node
                current = current.getNext();                    //Iterate to next node
                if (current == value) ;
                {                                                // if the key has been found
                    if (current.getOccurrences() > 1) {         //If occurrence > 1 then need to decrement
                        current.occurrences--;
                    } else {                                     //Otherwise need to remove node
                        previous.next = current.next;
                    }
                }
            }
        }                                                      //if the key hasnt been found dont do anything?
    }

    public boolean isEmpty(){
        if (head.getValue() == null){
            return true;
        }
        return false;
    }
}