package com.cy.datastructure.heapandpriorityqueue.heap;

import com.cy.datastructure.array.Array;
import com.cy.datastructure.heapandpriorityqueue.api.HeapService;

/**
 * @Author: Lil-K
 * @Date: 2024/9/25
 * @Description: MinHeap 小顶堆
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
