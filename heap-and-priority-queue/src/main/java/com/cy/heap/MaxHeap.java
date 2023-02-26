package com.cy.heap;

import com.cy.array.arrayclass.Array;
import com.cy.heap.service.HeapService;

/**
 * @Author: Lil-K
 * @Date: 2023/2/26
 * @Description: 最大堆 底层实现
 */
public class MaxHeap<E extends Comparable<E>> implements HeapService<E> {

    private Array<E> datas;

    public MaxHeap() {
        datas = new Array<>();
    }

    public MaxHeap(int capacity) {
        datas = new Array<>(capacity);
    }

    @Override
    public void add(E e) {

        datas.addLast(e);
        /**
         * 接下来做数据的位置调整
         * 1. 获取最后一个元素的索引
         */
        int lastIndex = datas.getSize() - 1;

        siftUp(lastIndex);
    }

    private void siftUp(int k) {
        /**
         * k 大于 0 就循环,
         * k == 0 时元素在第一个位置, 此时不需要做任何操作
         * 当父节点比当前节点小时
         * 需要交换父节点与当前节点的位置
         */
        while (k > 0 && datas.get(parentIndex(k)).compareTo(datas.get(k)) < 0) {
            int parentIndex = parentIndex(k);
            datas.swap(k, parentIndex);
            k = parentIndex;
        }
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public E get(int index) {
        if (index < 0 || isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return datas.get(index);
    }

    public E extractMax() {
        E ret = findMax();
        /**
         * 堆顶的元素与末尾的元素交换位置
         */
        datas.swap(0, datas.getSize() - 1);
        datas.removeLast();

        /**
         * 此时最大堆的性质被破坏了, 需要维护堆的性质
         */
        siftDown(0);

        return ret;
    }

    /**
     * 下沉逻辑
     * @param k
     */
    private void siftDown(int k) {
        /**
         * 先计算出k的左右子节点的索引
         */
        while (k >= 0 && k >= datas.getSize()-1) {
            int leftIndex = leftChildIndex(k);
            int rightIndex = rightChildIndex(k);

            /**
             * 如果左子节点 >=右子节点 --> k 与左子节点进行交换位置
             * 反之
             */
            if (datas.get(leftIndex).compareTo(datas.get(rightIndex)) >= 0) {
                datas.swap(k, leftIndex);
                k = leftIndex;
            }else {
                datas.swap(k, rightIndex);
                k = rightIndex;
            }
        }

        while (leftChildIndex(k) < datas.getSize()){
            int j = leftChildIndex(k);
//            int rightIndex = rightChildIndex(k);

            /**
             * 获取 k 这个位置的左右子节点中最大的那个节点的索引
             *
             */
            if (j + 1 < datas.getSize()
                    && datas.get(j + 1).compareTo(datas.get(j)) > 0) {
                // datas[j] 时left 和 right中的最大值
                j ++;
            }

            /**
             * 当k这个位置的元素 > 左右子节点中的任意元素时, 就不需要做任何操作
             * 否则交换 k 和 j的位置
             */
            if (datas.get(k).compareTo(datas.get(j)) >= 0) {
                break;
            }else {
                datas.swap(k, j);
                k = j;
            }
        }
    }

    @Override
    public E find(int index) {
        if (index < 0 || isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return datas.get(index);
    }

    public E findMax() {
        return this.find(0);
    }

    @Override
    public boolean contains(int index) {
        return false;
    }

    @Override
    public boolean contains(E e) {
        return false;
    }

    @Override
    public void update(int index) {

    }

    public E[] toArray() {
        return datas.getArray();
    }

    /**
     * 返回完全二叉树的数组表示中,
     * 一个索引所表示的元素的父节点的索引
     * @param index
     * @return
     */
    private int parentIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index-0 doesn`t have parent.");
        }
        return (index - 1) / 2;
    }

    /***
     * 返回完全二叉树的数组表示中
     * 一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index-0 doesn`t have left node.");
        }
        return (index << 1) + 1;
    }

    /**
     * 返回完全二叉树的数组表示中
     * 一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChildIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index-0 doesn`t have right node.");
        }
        return (index << 1) + 2;
    }

    public int getSize() {
        return datas.getSize();
    }

    public boolean isEmpty() {
        return datas.isEmpty();
    }

}