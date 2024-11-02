package com.cy.datastructure.stackandqueue.stackclass;

/**
 * @Author: Lil-K
 * @Date: 2023/2/21
 * @Description:
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    int getSize();

    E peek();

    boolean isEmpty();
}
