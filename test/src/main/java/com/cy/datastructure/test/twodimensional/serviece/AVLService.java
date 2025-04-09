package com.cy.datastructure.test.twodimensional.serviece;

/**
 * @Author: Lil-K
 * @Date: 2023/4/1
 * @Description:
 */
public interface AVLService<K, V> {

  void add(K key, V value);

  void remove(K key);

  K getMin();

  K getMax();

  boolean isEmpty();

  int getSize();
}
