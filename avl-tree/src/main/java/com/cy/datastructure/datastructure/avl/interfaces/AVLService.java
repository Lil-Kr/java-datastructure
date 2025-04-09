package com.cy.datastructure.datastructure.avl.interfaces;

/**
 * @Author: Lil-K
 * @Date: 2023/3/29
 * @Description:
 */
public interface AVLService<K extends Comparable<K>, V> {

  void add(K key, V value);

  void remove(K key);

  boolean contain(K key);

  boolean isEmpty();

  int getSize();
}
