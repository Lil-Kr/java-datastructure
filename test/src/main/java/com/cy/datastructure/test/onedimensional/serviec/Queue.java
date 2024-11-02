package com.cy.datastructure.test.onedimensional.serviec;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description:
 */
public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    E getFront();

    E getTail();

    boolean isEmpty();

    int getSize();

}
