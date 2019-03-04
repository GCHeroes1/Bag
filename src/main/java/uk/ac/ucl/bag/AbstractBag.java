package uk.ac.ucl.bag;

/**
 * This class implements methods common to all concrete bag implementations
 * but does not represent a complete bag implementation.<br />
 *
 * New bag objects are created using a BagFactory, which can be configured in the application
 * setup to select which bag implementation is to be used.
 */
import java.util.Iterator;

public abstract class AbstractBag<T extends Comparable> implements Bag<T> {
    public Bag<T> createMergedAllOccurrences(Bag<T> b) throws BagException {
        Bag<T> result = BagFactory.getInstance().getBag();
        for (T value : this) {
            result.addWithOccurrences(value, this.countOf(value));
        }
        for (T value : b) {
            result.addWithOccurrences(value, b.countOf(value));
        }
        return result;
    }

    public Bag<T> createMergedAllUnique(Bag<T> b) throws BagException {
        Bag<T> result = BagFactory.getInstance().getBag();
        for (T value : this) {
            if (!result.contains(value)) result.add(value);
        }
        for (T value : b) {
            if (!result.contains(value)) result.add(value);
        }
        return result;
    }

    public String toString() {
        Boolean check = false;
        Bag<T> bag = null;
        try {
            bag = BagFactory.getInstance().getBag();
        } catch (BagException e) {
            e.printStackTrace();
        }
        String result = "[ ";
        for (T value : this) {
            if (check == false) {
                check = true;
            } else {
                result = result + ", ";
            }
            if (!bag.contains(value)) {
                result = result + value + ": ";
            }
            result = result + this.countOf(value);
            if (bag.allOccurrencesIterator().hasNext()) {
                result = result + ",";
            }
        }
        result = result + "]";
        return result;
    }

    public void removeAllCopies() {
        /*Bag<T> bag = null;
        try {
            bag = BagFactory.getInstance().getBag();
        } catch (BagException e) {
            e.printStackTrace();
        }*/
        for (T value : this) {
            if (this.countOf(value) != 1) {
                while (this.countOf(value) != 1) {
                    //System.out.println(value);
                    //System.out.print(this.countOf(value));
                    this.remove(value);
                }
            }
        }
    }

    public Bag<T> subtract(Bag<T> bag) throws  BagException {
        Bag<T> result = BagFactory.getInstance().getBag();
        for (T value : this) {
            if (!result.contains(value)) {
                result.addWithOccurrences(value, this.countOf(value));
            }
        }
        for (T value : bag) {
            if (result.contains(value)) {
                result.remove(value);
            }
        }
        return result;
    }
}
