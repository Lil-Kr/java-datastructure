package com.cy.datastructure.test.twodimensional.segmenttree;

/**
 * @Author: Lil-K
 * @Date: 2023/8/20
 * @Description: 线段树的业务规则
 */
@FunctionalInterface
public interface Merger<E> {

  E merge(E a, E b);
}
