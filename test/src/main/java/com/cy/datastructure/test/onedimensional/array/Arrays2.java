package com.cy.datastructure.test.onedimensional.array;

import com.cy.datastructure.test.onedimensional.serviec.ArrayService;

/**
 * @Author: Lil-K
 * @Date: 2023/5/14
 * @Description:
 *
 */
public class Arrays2<E> implements ArrayService<E> {

    private int DEFAULT_CAPACITY = 10;

    private E[] data;

    private int size;

    public Arrays2() {
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public Arrays2(int capacity) {
        this.data = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("add method index is error");
        }

        if (index == 0) {
        }else {
        }
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size-1, e);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E e) {

    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getCapacity() {
        return this.data.length;
    }

    @Override
    public E[] toArray() {
        return null;
    }
}