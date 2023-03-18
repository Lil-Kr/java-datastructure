package com.cy.test.twodimensional.heap.service;


/**
 * @Author: Lil-K
 * @Date: 2023/2/26
 * @Description:
 */
public interface HeapService<E> {

    /**
     * 添加元素
     * @param e
     */
    void add(E e);

    /**
     * 查看最小的元素
     * @return
     */
    default E findMin(){
        return null;
    }

    default E findMax() {
        return null;
    }
    default E extractMax() {
        return null;
    }

    default E extractMin() {
        return null;
    }

    E replace(E e);

    void heapify(E[] arr);

    boolean isEmpty();

    int getSize();

}