package com.cy.heapandpriorityqueue.service;

import com.cy.array.arrayclass.Array;

/**
 * @Author: Lil-K
 * @Date: 2023/2/26
 * @Description:
 */
public interface HeapService<E> {

    void add(E e);

    void remove(E e);

    default E extractMax() {
        return null;
    }

    default E extractMin() {
        return null;
    }

    default E findMin(){
        return null;
    }

    default E findMax() {
        return null;
    }

    E replace(E e);

    default void heapify(Array array){}

    default void heapifyIndexHeap(Integer[] indexs){}

    boolean isEmpty();

    int getSize();
}