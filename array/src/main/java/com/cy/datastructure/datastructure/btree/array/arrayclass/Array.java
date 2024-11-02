package com.cy.datastructure.datastructure.btree.array.arrayclass;

import com.alibaba.fastjson2.JSON;

/**
 * @Author: Lil-K
 * @Date: 2023/2/18
 * @Description:
 */

public class Array<E extends Comparable<E>> {
    private E[] data;

    private int size;

    private static int DEFAULT_CAPACITY = 10;

    /**
     * init array
     * @param capacity
     */
    public Array(int capacity) {
        this.data = (E[]) new Comparable[capacity];
        this.size = 0;
    }

    public Array(){
        this(DEFAULT_CAPACITY);
    }

    public Array(E[] arr){
        this.data = (E[]) new Comparable[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public E[] getArray () {
        return data;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Comparable[newCapacity];
        for (int i = 0; i < this.data.length; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    /**
     * 向数组中添加元素
     * 添加元素之前考虑容量是否足够
     * 向任意位置添加元素, 包括头插, 中间位置
     * 向数组末尾添加元素
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size + 2) {
            throw new IllegalArgumentException("add index was error");
        }

        /**
         * 1. 判断数组长度是否足够
         * 2. 不足够的话需要扩容, 并保持copy原来的元素
         */
        if(size == getCapacity()) {
            resize(getCapacity() + (getCapacity() >> 1));
        }

        /**
         * 在末尾添加元素
         */
        if (index == size + 1) {
            data[size] = e;
        }else {// 在任意位置添加元素
            for (int i = size; i > index; i--) {
                data[i] = data[i-1];
            }
            data[index] = e;
        }
        size ++;
    }

    public void addFirst(E data) {
        add( 0,data);
    }

    public void addLast(E data) {
        add(size,data);
    }

    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("remove index was error");
        }

        E removeData = data[index];

        /**
         * 1. 删除末尾元素
         * 2. 删除非末尾元素
         */
        if(index == size-1) {
            data[index] = null;
        }else {
            // 调整数组
            for (int i = index; i < size-1; i++) {
                data[i] = data[i + 1];
            }
            data[size-1] = null;
        }
        size--;

        /**
         * todo 缩容的情况
         */
        if (size >= DEFAULT_CAPACITY && size <= getCapacity() / 4) {
            resize(getCapacity() >> 1);
        }
        return removeData;
    }

    public E removeFirst() {
        return this.remove(0);
    }

    public E removeLast() {
        return this.remove(size-1);
    }


    /**
     * 根据索引获取对应元素
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || size <= 0) {
            throw new IllegalArgumentException("index is error or element was empty");
        }
        return data[index];
    }

    public E getFirst() {
        return this.get(0);
    }

    public void set(int index, E e) {
        if (index < 0 || size <= 0) {
            throw new IllegalArgumentException("index is error for Array set operation");
        }

        data[index] = e;
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
        E[] ret = (E[]) new Comparable[size];

        for (int i = 0; i < size; i++) {
            ret[i] = data[i];
        }
        return ret;
    }

    @Override
    public String toString() {
        return "Array{" +
                "data=" + JSON.toJSONString(data) +
                ", size=" + size +
                ", capacity=" + getCapacity() +
                '}';
    }

}
