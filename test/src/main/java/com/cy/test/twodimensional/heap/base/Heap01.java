package com.cy.test.twodimensional.heap.base;

import com.alibaba.fastjson2.JSON;
import com.cy.test.twodimensional.heap.service.HeapService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Lil-K
 * @Date: 2023/3/31
 * @Description: 最大堆
 */
public class Heap01<E extends Comparable<E>> implements HeapService<E> {

    private List<E> data;

    public Heap01() {
        data = new ArrayList<>();
    }

    public Heap01(E[] arr) {
        data = new ArrayList<>(Arrays.asList(arr));
        if (arr != null && arr.length > 0) {
            heapify(arr);
        }
    }

    private int parentIndex(int k) {
        return (k - 1) / 2;
    }

    private int leftChildIndex(int k) {
        return k * 2 + 1;
    }

    private int rightChildIndex(int k) {
        return k * 2 + 2;
    }

    private void siftUp(int k) {
        while (parentIndex(k) >= 0 && data.get(parentIndex(k)).compareTo(data.get(k)) < 0) {
            /**
             * 交换位置
             */
            swap(parentIndex(k), k);
            k = parentIndex(k);
        }
    }

    private void siftDown(int k) {
        while (leftChildIndex(k) < data.size()) {
            int j = leftChildIndex(k);

            if ((j + 1) < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            this.swap(k, j);
            k = j;
        }
    }

    private void swap(int j, int k) {
        E temp = data.get(j);
        data.set(j, data.get(k));
        data.set(k, temp);
    }

    /**
     * 往后添加元素, 然后上浮操作
     * @param e
     */
    @Override
    public void add(E e) {
        if (data.contains(e) || Objects.isNull(e)) {
            return;
        }
        data.add(e);
        siftUp(this.getSize() - 1);
    }

    @Override
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return data.get(0);
    }

    @Override
    public E extractMax() {
        if (isEmpty()) {
            return null;
        }

        E max = findMax();

        swap(0, getSize()-1);
        data.remove(getSize() - 1);
        siftDown(0);
        return max;
    }

    @Override
    public E replace(E e) {
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }

    @Override
    public void heapify(E[] arr) {
        /**
         * 找到第一个非叶子节点
         */
        int parentIndex = parentIndex(arr.length - 1);
        for (int i = parentIndex; i >=0 ; i--) {
            siftDown(i);
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(data);
    }
}
