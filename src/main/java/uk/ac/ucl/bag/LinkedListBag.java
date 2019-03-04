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

        private ArrayList<T> getList() {
            Element<T> current = head;
            ArrayList<T> listOfElements = new ArrayList<>();
            while (current != null) {
                for (int i = 0; i < current.getOccurrences(); i++)
                {
                    listOfElements.add(current.getValue());
                }
                current = current.getNext();
            }
            return listOfElements;
        }

        public boolean hasNext() {
            if (index < getList().size()) {
                index++;
                return true;
            }
            return false;
        }

        public T next() {
            ArrayList<T> allOccurrences = getList();
            count=1;
            for (T item : allOccurrences)
            {
                if (index == count)
                {
                    return item;
                }
                count++;
            }
            return null;
            // in has next, need a variable called index which tells me which iteration i'm on, (index starts at 0)
            // need a for loop in next which starts with count at 1,  and loops around until count = index
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
        for (int i = 0; i < occurrences; i++) {
            add(value);
        }
    }

    public boolean contains(T value) {
        Element<T> current = head;
        while (current != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
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
                if (current.value == value)
                {                                                // if the key has been found
                    if (current.occurrences > 1) {         //If occurrence > 1 then need to decrement
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