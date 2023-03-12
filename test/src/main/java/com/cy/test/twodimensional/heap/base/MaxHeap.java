package com.cy.test.twodimensional.heap.base;

import com.cy.test.twodimensional.heap.helper.Array;
import com.cy.test.twodimensional.heap.service.HeapService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/12
 * @Description:
 */
public class MaxHeap<E extends Comparable<E>> implements HeapService<E> {

    @Override
    public void add(E e) {

    }

    @Override
    public void remove(E e) {

    }

    @Override
    public E extractMax() {
        return HeapService.super.extractMax();
    }

    @Override
    public E findMax() {
        return HeapService.super.findMax();
    }

    @Override
    public E replace(E e) {
        return null;
    }

    @Override
    public void heapify(Array array) {

    }
}
