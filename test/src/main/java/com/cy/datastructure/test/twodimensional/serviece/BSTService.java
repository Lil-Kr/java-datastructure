package com.cy.datastructure.test.twodimensional.serviece;

/**
 * @Author: Lil-K
 * @Date: 2023/3/3
 * @Description:
 */
public interface BSTService<E> {

    void add(E e);

    E getMin();

    E getMax();

    void remove(E e);

    E removeMin();

    E removeMax();

    void set(E oldV, E newV);

    boolean contain(E e);

    void preOrder();

    void preOrderNR();

    void inOrder();

    void endOrder();

    void levelOrder();

    int getSize();

    boolean isEmpty();
}
