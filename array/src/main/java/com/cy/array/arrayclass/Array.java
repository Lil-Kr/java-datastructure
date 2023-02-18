package com.cy.array.arrayclass;

/**
 * @Author: Lil-K
 * @Date: 2023/2/18
 * @Description:
 */
public class Array<T> {
    private T[] data;

    private int size;

    private static int DEFAULT_CAPACITY = 10;

    /**
     * init Array
     * @param capacity
     */
    public Array(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
    }

    public Array(){
        this(DEFAULT_CAPACITY);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public T get(int index){
        if (index < 0) {
            throw new IllegalArgumentException("index is error");
        }
        return data[index];
    }

}
