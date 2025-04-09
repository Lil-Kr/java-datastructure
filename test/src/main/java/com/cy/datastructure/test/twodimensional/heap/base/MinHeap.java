package com.cy.datastructure.test.twodimensional.heap.base;

import com.cy.datastructure.test.twodimensional.heap.service.HeapService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/3/13
 * @Description: 最小堆
 */
public class MinHeap<E extends Comparable<E>> implements HeapService<E> {

  private List<E> array;

  public MinHeap() {
    this.array = new ArrayList<>();
  }

  public MinHeap(int capacity) {
    this.array = new ArrayList<>(capacity);
  }

  public MinHeap(E[] arr) {
    this.array = new ArrayList<>(Arrays.asList(arr));
    this.heapify(arr);
  }

  private void siftUp(int k) {
    while (k > 0 && array.get(k).compareTo(array.get(this.parentIndex(k))) < 0) {
      this.swap(k, this.parentIndex(k));
      k = this.parentIndex(k);
    }
  }

  private void siftDown(int k) {
    while (leftChildIndex(k) < array.size()) { // 说明还未越界, k节点一定存在 左子节点
      int j = leftChildIndex(k);

      if ((j + 1) < array.size() && array.get(j+1).compareTo(array.get(j)) < 0) { // 说明k节点一定存在 右子节点
        j++;
      }

      /**
       * 父节点和最小的子节点交换位置
       */
      if (array.get(k).compareTo(array.get(j)) <= 0) {
        break;
      }
      this.swap(k, j);
      k = j;
    }
  }

  private int parentIndex(int k) {
    if (k <= 0) {
      throw new IllegalArgumentException("index is error");
    }
    return (k - 1) / 2;
  }

  private int leftChildIndex(int k) {
    return (k * 2) + 1;
  }

  private int rightChildIndex(int k) {
    return (k * 2) + 2;
  }

  private void swap (int i, int j) {
    if (i < 0 || j < 0 || i >= getSize() || j >= getSize()) {
      throw new IllegalArgumentException("swap method: index is illegal");
    }
    E temp = array.get(i);
    array.set(i, array.get(j));
    array.set(j, temp);
  }

  @Override
  public void add(E e) {
    if (array.contains(e)) {
      return;
    }

    array.add(e);
    siftUp(array.size()-1);
  }

  @Override
  public E extractMin() {
    if (isEmpty()) {
      return null;
    }

    E min = this.findMin();

    this.swap(0,array.size()-1);
    array.remove(array.size() - 1);
    siftDown(0);
    return min;
  }

  @Override
  public E findMin() {
    if (isEmpty()) {
      return null;
    }
    return array.get(0);
  }

  @Override
  public E replace(E e) {
    E min = this.findMin();
    array.set(0, e);
    siftDown(0);
    return min;
  }

  @Override
  public void heapify(E[] arr) {
    int parentIndex = this.parentIndex(arr.length - 1);
    for (int i = parentIndex; i >= 0; i--) {
      siftDown(i);
    }
  }

  @Override
  public boolean isEmpty() {
    return array.isEmpty();
  }

  @Override
  public int getSize() {
    return array.size();
  }

}
