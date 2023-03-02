package com.cy.test.onedimensional.array;

import com.cy.test.onedimensional.serviec.ArrayService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description: 动态数组
 */
public class Arrays<E extends Comparable<E>> implements ArrayService<E> {

    private int size;

    private E[] data;

    private static int DEFAULT_CAPACITY = 10;

    public Arrays() {
        this(DEFAULT_CAPACITY);
    }

    public Arrays(int capacity) {
        data = (E[]) new Comparable[capacity];
        size = 0;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add method index is error ");
        }

        /**
         * 扩容
         */
        if (size == this.getCapacity()) {
            resize(this.getCapacity() + (this.getCapacity() >> 1));
        }

        if (index == size + 1) {
            data[size] = e;
        }else {
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = e;
        }
        size ++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addFirst(E e) {
        this.add(0, e);
    }

    public void addLast(E e) {
        this.add(size, e);
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size || isEmpty()) {
            throw new IllegalArgumentException("remove method index is error");
        }

        E del = data[index];
        if (index == size) {
            data[index] = null;
        }else {
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            // 将最后一个元素置为空
            data[size-1] = null;
        }
        size--;

        /**
         * 缩容
         */
        if (size >= DEFAULT_CAPACITY && size <= getCapacity() / 4) {
            resize(getCapacity() >> 1);
        }

        return del;
    }

    public E removeLast(){
        return this.remove(size-1);
    }

    public E removeFirst(){
        return this.remove(0);
    }

    @Override
    public Boolean contains(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("contains method -> array is empty");
        }

        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("get method index is error ");
        }

        return data[index];
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("getFirst method data was empty");
        }
        return data[0];
    }

    public E getLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("getLast method data was empty");
        }
        return data[size-1];
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("set method index is error ");
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

    @Override
    public E[] toArray() {
        E[] newData = (E[]) new Comparable[size];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        return newData;
    }
}
