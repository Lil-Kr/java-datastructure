package com.cy.datastructure.setandmap.service;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description:
 */
public interface MapService<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V value);

    int getSize();

    boolean isEmpty();
}
