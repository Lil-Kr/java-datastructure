package com.cy.datastructure.segmenttree.interfaces;

/**
 * @Author: Lil-K
 * @Date: 2023/3/17
 * @Description:
 */
public interface Rule<E> {

    E merge(E a, E b);

}
