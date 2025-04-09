package com.cy.datastructure.test.twodimensional.segmenttree;

/**
 * @Author: Lil-K
 * @Date: 2023/8/20
 * @Description:
 */
public interface SegmentTreeService<E> {


  E query(int queryL, int queryR);

  void update(int updateIndex, E e);

  E get(int index);

  Boolean isEmpty();

  int getSize();
}
