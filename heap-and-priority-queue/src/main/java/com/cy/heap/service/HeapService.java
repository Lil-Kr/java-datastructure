package com.cy.heap.service;

/**
 * @Author: Lil-K
 * @Date: 2023/2/26
 * @Description:
 */
public interface HeapService<E> {

    void add(E e);

    void remove(E e);

    E get(int index);

    E find(int index);

    boolean contains(int index);

    boolean contains(E e);

    void update(int index);


}
