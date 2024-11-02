package com.cy.datastructure.test.onedimensional.serviec;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description:
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    boolean isEmpty();

    int getSize();
}
