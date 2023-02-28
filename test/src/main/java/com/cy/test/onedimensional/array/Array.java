package com.cy.test.onedimensional.array;

/**
 * @Author: Lil-K
 * @Date: 2023/2/28
 * @Description:
 */
public class Array<E extends Comparable<E>> implements ArrayService<E> {

    private int size;

    private E[] data;

    private static int DEFAULT_CAPACITY = 10;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity) {
        data = (E[]) new Comparable[capacity];
        size = 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(this.getSize(), e);
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add element index was error");
        }

        /**
         * 调整容量  -> 扩容
         */
        if (size == this.getCapacity()) {
            resize(this.getCapacity() + (this.getCapacity() >> 1));
        }

        if (index == size) {
            data[size] = e;
        }else {
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = e;
        }
        size ++;
    }

    /**
     * 调整数组容量
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        Comparable[] newData = new Comparable[newCapacity];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        this.data = (E[]) newData;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("add element index was error");
        }

        E e = this.get(index);
        if (index == size-1) {
            data[index] = null;
        }else {
            for (int i = index; i < size-1; i++) {
                data[i] = data[i + 1];
            }
            data[size-1] = null;
        }
        size--;

        /**
         * 考虑缩容
         */
        if (size >= DEFAULT_CAPACITY && size <= (getCapacity() / 4)){
            resize(getCapacity() >> 1);
        }
        return e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public Boolean contains(E e) {
        return null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get element index was error");
        }

        return data[index];
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get element index was error");
        }

        data[index] = e;
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    public void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public E[] toArray() {
        Comparable[] ret = new Comparable[size];

        for (int i = 0; i < size; i++) {
            ret[i] = data[i];
        }
        return (E[]) ret;
    }
}
