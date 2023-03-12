package com.cy.test.twodimensional.heap.service;


import com.cy.test.twodimensional.heap.helper.Array;

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

    void heapify(Array array);

}