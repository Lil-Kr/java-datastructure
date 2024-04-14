package com.cy.heapandpriorityqueue.heap;

import com.cy.array.arrayclass.Array;
import com.cy.heapandpriorityqueue.api.HeapService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/12
 * @Description: 最小堆 min heap
 */
public class MinHeap<E extends Comparable<E>> implements HeapService<E> {

    @Override
    public void add(E e) {

    }

    @Override
    public E extractMin() {
        return HeapService.super.extractMin();
    }

    @Override
    public E findMin() {
        return HeapService.super.findMin();
    }

    @Override
    public E replace(E e) {
        return null;
    }

    @Override
    public void heapify(Array array) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
