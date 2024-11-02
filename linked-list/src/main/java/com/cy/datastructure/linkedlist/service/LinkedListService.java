package com.cy.datastructure.linkedlist.service;

/**
 * @Author: Lil-K
 * @Date: 2023/3/2
 * @Description:
 */
public interface LinkedListService<E> {

    void add(int index, E e);

    E remove(int index);

    E get(int index);

    void set(int index, E e);

    boolean contain();

    int getSize();

    boolean isEmpty();

    E[] toArray();
}
