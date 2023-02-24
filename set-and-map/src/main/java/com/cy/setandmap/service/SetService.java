package com.cy.setandmap.service;

import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description:
 */
public interface SetService<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    List<E> getSetBSTs();

    int getSize();

    boolean isEmpty();
}
