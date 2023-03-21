package com.cy.test.twodimensional.heap.base;

import com.alibaba.fastjson2.JSON;
import com.cy.test.twodimensional.heap.service.HeapService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Author: Lil-K
 * @Date: 2023/3/12
 * @Description: 最大堆
 */
public class MaxHeap<E extends Comparable<E>> implements HeapService<E> {

    private ArrayList<E> array;

    public MaxHeap() {
        this.array = new ArrayList<>();
    }

    public MaxHeap(int capacity) {
        this.array = new ArrayList<>(capacity);
    }

    public MaxHeap(E[] arr) {
        this.array = new ArrayList<>(Arrays.asList(arr));
        this.heapify(arr);
    }

    /**
     * 添加元素
     * @param e
     */
    @Override
    public void add(E e) {
        if (array.contains(e) || Objects.isNull(e)) {
            return;
        }
        array.add(e);
        siftUp(array.size() - 1);
    }

    /**
     * 提取最大的元素
     * 1. 移除数组第一个元素
     * 2. 用最后一个元素来替换第一个元素的空缺
     * 3. 在通过下沉操作, 调整整个堆, 使它保持堆的性质
     * @return
     */
    @Override
    public E extractMax() {
        /**
         * 取出第一个元素
         * 这个元素就是最大的元素
         */
        E max = findMax();

        /**
         * 交换第一个元素与最后一个元素的位置
         */
        this.swap(0, array.size()-1);

        /**
         * 删除最后一个元素
         */
        array.remove(array.size() - 1);
        siftDown(0);
        return max;
    }

    @Override
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return array.get(0);
    }

    @Override
    public E replace(E e) {
        E max = this.findMax();
        array.set(0, e);
        siftDown(0);
        return max;
    }

    @Override
    public void heapify(E[] arr) {
        /**
         * 找到最后一个非叶子节点的索引, 就是最后一个元素的 parentIndex
         * 找到这个索引和之前的索引的所有元素, 都依次做 siftDown 操作
         */
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

    @Override
    public String toString() {
        return JSON.toJSONString(array);
    }

    /**
     * 计算父节点索引
     * @param index
     * @return
     */
    private int parentIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index-0 doesn`t have parent node");
        }
        return (index - 1) / 2;
    }

    /**
     * 计算左子节点的索引值
     * @param index
     * @return
     */
    private int leftChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * 计算右子节点的索引值
     * @param index
     * @return
     */
    private int rightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * 元素上浮
     */
    private void siftUp(int k) {
        while (k > 0 && array.get(parentIndex(k)).compareTo(array.get(k)) < 0) {
            this.swap(k, parentIndex(k));
            k = parentIndex(k);
        }
    }

    /**
     * 元素下沉
     */
    private void siftDown(int k) {

        /**
         * 此情况表示 -> k 这个节点一定有左子节点
         */
        while (leftChildIndex(k) < array.size()) {
            int j = leftChildIndex(k);
            /**
             * 如果有右子节点,
             * 并且右子节点 > 左子节点
             */
            if ((j + 1) < array.size() && array.get(j+1).compareTo(array.get(j)) > 0) {
                j++;
                // j = rightChildIndex(k);
            }

            /**
             * k元素已经比所有子节点中的元素都大了
             * 这时就不需要做操作了
             */
            if (array.get(k).compareTo(array.get(j)) >= 0) {
                break;
            }

            /**
             * 重要的步骤
             */
            this.swap(k,j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= getSize() || j >= getSize()) {
            throw new IllegalArgumentException("swap method: index is illegal");
        }
        E temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}