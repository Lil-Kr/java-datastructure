package com.cy.test.twodimensional.heap.base;

import com.cy.test.twodimensional.heap.service.HeapService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Lil-K
 * @Date: 2023/3/14
 * @Description:
 */
public class MaxHeap2<E extends Comparable<E>> implements HeapService<E> {

    private List<E> array;

    public MaxHeap2() {
        this.array = new ArrayList<>();
    }

    public MaxHeap2(int capacity) {
        this.array = new ArrayList<>(capacity);
    }

    public MaxHeap2(E[] arr) {
        this.array = new ArrayList<>(Arrays.asList(arr));
        this.heapify(arr);
    }

    private int getLastIndex() {
        return this.array.size()-1;
    }

    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= this.getSize() || j >= this.getSize()) {
            throw new IllegalArgumentException("swap method indexs is error");
        }
        E temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    private int parentIndex(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("method parentIndex index is error");
        }
        return (k - 1) / 2;
    }

    private int leftChildIndex(int k) {
        return (k * 2) + 1;
    }

    private int rightChildIndex(int k) {
        return (k * 2) + 2;
    }

    private E get(int index) {
        return array.get(index);
    }

    private void siftUp(int k) {
        while (k > 0 && array.get(k).compareTo(array.get(this.parentIndex(k))) > 0) {
            this.swap(k, this.parentIndex(k));
            k = this.parentIndex(k);
        }
    }

    private void siftDown(int k) {
        while (this.leftChildIndex(k) < this.getSize()) {// 有左子节点
            // 待替换的元素索引
            int j = this.leftChildIndex(k);

            if ((j + 1) < this.getSize() && this.get(j + 1).compareTo(this.get(j)) > 0) {// 有右子节点
                j++;
            }

            if (get(k).compareTo(get(j)) >= 0) {
                break;
            }

            this.swap(k, j);
            k = j;
        }
    }

    @Override
    public void add(E e) {
        if (e == null) {
            return;
        }
        // 从最后一个位置添加
        array.add(e);
        // 上浮操作, 维护堆的性质
        siftUp(this.getLastIndex());
    }

    @Override
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return this.get(0);
    }

    @Override
    public E extractMax() {
        E max = findMax();

        // 先交换堆顶元素与末尾元素
        this.swap(0,this.getLastIndex());

        // 移除最后一个元素
        this.array.remove(getLastIndex());

        // 调整位置, 维持堆的性质
        siftDown(0);
        return max;
    }

    @Override
    public E replace(E e) {
        E max = findMax();
        array.set(0, e);
        siftDown(0);
        return max;
    }

    @Override
    public void heapify(E[] arr) {
        if (Objects.isNull(arr) || arr.length <= 0) {
            throw new IllegalArgumentException("arr parameter is error");
        }

        /**
         * 找到最后一个非叶子节点
         */
        int parentIndex = parentIndex(arr.length - 1);
        for (int i = parentIndex; i >= 0; i--) {
            siftDown(i);
        }
    }

    @Override
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    @Override
    public int getSize() {
        return this.array.size();
    }
}
