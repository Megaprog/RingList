package org.jmmo;

import java.lang.Override;import java.lang.UnsupportedOperationException;import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class RingList<E> extends AbstractList<E> implements RandomAccess {

    private final List<E> buf;
    private final E defaultValue;

    public RingList(int capacity) {
        this(capacity, null);
    }

    public RingList(int capacity,E defaultValue) {
        buf = new ArrayList<E>(Collections.nCopies(capacity, defaultValue));
        this.defaultValue = defaultValue;
    }

    public void shiftLeft(int startIndex, int endIndex, int number) {
        for (int i = endIndex - number; i >= startIndex; i--) {
            set(i + number, get(i));
        }

        clear(startIndex, endIndex);
    }

    public void shiftLeft(int number) {
        shiftLeft(0, size() - 1, number);
    }

    public void shiftLeft() {
        shiftLeft(1);
    }

    public void shiftRight(int startIndex, int endIndex, int number) {
        for (int i = startIndex; i <= endIndex; i++) {
            set(i - number, get(i));
        }

        clear(startIndex, endIndex);
    }

    public void shiftRight(int number) {
        shiftRight(0, size() - 1, number);
    }

    public void shiftRight() {
        shiftRight(1);
    }

    public void fill(int startIndex, int endIndex, E e) {
        for (int i = startIndex; i <= endIndex; i++) {
            set(i, e);
        }
    }

    public void fill(E e) {
        fill(0, size() - 1, e);
    }

    public void clear(int startIndex, int endIndex) {
        fill(startIndex, endIndex, defaultValue);
    }

    @Override
    public void clear() {
        clear(0, size() - 1);
    }

    @Override
    public boolean add(E e) {
        shiftLeft();
        set(0, e);
        return true;
    }

    public boolean addFirst(E e) {
        shiftRight();
        set(size() - 1, e);
        return true;
    }

    public boolean addLast(E e) {
        return add(e);
    }

    @Override
    public int size() {
        return buf.size();
    }

    @Override
    public E get(int i) {
        return buf.get(i);
    }

    @Override
    public E set(int i, E e) {
        return buf.set(i, e);
    }

    @Override
    public void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int i) {
        throw new UnsupportedOperationException();
    }
}