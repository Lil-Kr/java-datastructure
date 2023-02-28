package com.cy.test.onedimensional.array;

/**
 * @Author: Lil-K
 * @Date: 2023/2/28
 * @Description:
 */
public interface ArrayService<E> {

    void add(int index, E e);

    E remove(int index);

    Boolean contains(E e);

    E get(int index);

    void set(int index, E e);

    Integer getSize();

    Boolean isEmpty();

    int getCapacity();

}
