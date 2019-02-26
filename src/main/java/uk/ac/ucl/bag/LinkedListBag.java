package uk.ac.ucl.bag;

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedListBag<T extends Comparable> extends AbstractBag<T> {
    private int maxSize;
    private static class Element<E extends Comparable>