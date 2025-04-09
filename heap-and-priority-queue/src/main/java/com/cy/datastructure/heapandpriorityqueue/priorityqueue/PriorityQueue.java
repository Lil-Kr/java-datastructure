package com.cy.datastructure.heapandpriorityqueue.priorityqueue;

import com.cy.datastructure.heapandpriorityqueue.heap.MaxHeap;
import com.cy.datastructure.heapandpriorityqueue.api.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/2/27
 * @Description: 基于大顶堆 实现 优先队列
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

  private MaxHeap<E> maxHeap;

  public PriorityQueue() {
    maxHeap = new MaxHeap<>();
  }

  /**
   * 入队
   * @param e
   */
  @Override
  public void enqueue(E e) {
    maxHeap.add(e);
  }

  @Override
  public E dequeue() {
    return maxHeap.extractMax();
  }

  @Override
  public E getFront() {
    return maxHeap.findMax();
  }

  @Override
  public int getSize() {
    return maxHeap.getSize();
  }

  @Override
  public boolean isEmpty() {
    return maxHeap.isEmpty();
  }
}