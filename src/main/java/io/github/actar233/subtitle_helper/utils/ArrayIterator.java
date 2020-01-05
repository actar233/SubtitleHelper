package io.github.actar233.subtitle_helper.utils;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {

    private int current;

    private long size;

    private T[] list;

    public ArrayIterator(T[] list) {
        this.list = list;
        this.size = list.length;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return size > current;
    }

    @Override
    public T next() {
        return list[current++];
    }
}
