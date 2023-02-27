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

    E extractMax();

    E findMax();

    E replace(E e);

    void heapify(Array array);

}