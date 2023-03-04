package com.cy.test.twodimensional.serviece;

/**
 * @Author: Lil-K
 * @Date: 2023/3/3
 * @Description:
 */
public interface BSTService<E> {

    void add(E e);

    void remove(E e);

    E removeMin();

    E removeMax();

    void set(E e);

    E getMax();

    E getMin();

    boolean contain(E e);

    void preOrder();

    void inOrder();

    void endOrder();

    void levelOrder();

    int getSize();

    boolean isEmpty();
}
