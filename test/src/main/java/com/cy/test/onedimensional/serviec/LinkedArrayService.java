package com.cy.test.onedimensional.serviec;

/**
 * @Author: Lil-K
 * @Date: 2023/2/28
 * @Description:
 */
public interface LinkedArrayService<E> {

    void add(int index, E e);

    E remove(int index);

    E get(int index);

    void set(int index, E e);

    boolean contain(E e);

    int getSize();

    boolean isEmpty();

    Comparable[] toArray();

}
