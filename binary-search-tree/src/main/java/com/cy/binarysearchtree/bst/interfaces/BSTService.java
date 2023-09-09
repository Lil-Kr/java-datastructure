package com.cy.binarysearchtree.bst.interfaces;
/**
 * @Author: Lil-K
 * @Date: 2023/2/23
 * @Description:
 */
public interface BSTService<E>{

    void add(E e);

    boolean contains(E e);

    void update(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
