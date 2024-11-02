package com.cy.datastructure.test.twodimensional.heap.service;

/**
 * @Author: Lil-K
 * @Date: 2023/2/22
 * @Description: 
 */
public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();
}
