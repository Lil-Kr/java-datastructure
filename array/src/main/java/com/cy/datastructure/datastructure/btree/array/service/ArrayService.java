package com.cy.datastructure.datastructure.btree.array.service;

/**
 * @Author: Lil-K
 * @Date: 2023/2/28
 * @Description:
 */
public interface ArrayService<E> {

    void add(int index, E e);

    E remove(int index);

    boolean contains(E e);

    E get(int index);

    void set(int index, E e);

    int getSize();

    boolean isEmpty();

    int getCapacity();

    E[] toArray();

}
