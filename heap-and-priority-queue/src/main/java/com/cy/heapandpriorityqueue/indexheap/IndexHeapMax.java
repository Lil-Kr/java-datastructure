package com.cy.heapandpriorityqueue.indexheap;

import com.cy.array.arrayclass.Array;
import com.cy.heapandpriorityqueue.api.HeapService;

/**
 * @Author: Lil-K
 * @Date: 2023/2/26
 * @Description: 索引堆
 *
 * 思想:
 *
 */
public class IndexHeapMax<E extends Comparable<E>> implements HeapService<E> {

    private Array<E> datas;
    private Integer[] indexs;

    public IndexHeapMax() {
        datas = new Array<>();
        indexs = new Integer[0];
    }

    public IndexHeapMax(E[] arr) {
        datas = new Array<>(arr);
        indexs = new Integer[arr.length];
        heapifyIndexHeap(indexs);
    }

    public IndexHeapMax(int capacity) {
        datas = new Array<>(capacity);
        indexs = new Integer[capacity];
    }

    @Override
    public void add(E e) {
        datas.addLast(e);
        /**
         * 接下来做数据的位置调整
         * 1. 获取最后一个元素的索引
         */
        int lastIndex = datas.getSize() - 1;

        shiftUp(lastIndex);
    }

    private void shiftUp(int k) {
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
        shiftDown(0);
        return ret;
    }

    private E get(int index) {
        if (index < 0 || isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return datas.get(index);
    }

    /**
     * 下沉逻辑
     * @param k
     */
    private void shiftDown(int k) {
        while (leftChildIndex(k) < datas.getSize()){
            int j = leftChildIndex(k);

            /**
             * 获取 k 这个位置的左右子节点中最大的那个节点的索引
             */
            if (j + 1 < datas.getSize() && datas.get(j + 1).compareTo(datas.get(j)) > 0) {
                // datas[j] 时left 和 right中的最大值
                j ++;
            }

            /**
             * 当k这个位置的元素 > 左右子节点中的任意元素时, 就不需要做任何操作
             * 否则交换 k 和 j 的位置
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
    public E findMax() {
        return find(0);
    }

    private E find(int index) {
        if (index < 0 || isEmpty()) {
            throw new IllegalArgumentException("Can not find when heap is empty");
        }
        return datas.get(index);
    }

    /**
     * 替换堆顶元素
     * @param e
     * @return
     */
    @Override
    public E replace(E e) {
        E ret = findMax();

        // 堆顶替换成 e
        datas.set(0, e);
        shiftDown(0);
        return ret;
    }

    /**
     * 将数组整理为堆的结构
     * 这个过程的复杂度为: O(n)
     * @param indexs
     */
    @Override
    public void heapifyIndexHeap(Integer[] indexs) {
        for (int i = parentIndex(indexs.length - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    public E[] getArray() {
        Comparable[] arr = new Comparable[getSize()];

        for (int i = 0; i < datas.getSize(); i++) {
            arr[i] = datas.get(i);
        }
        return (E[]) arr;
    }

    /**
     * 返回完全二叉树的数组表示中,
     * 一个索引所表示的元素的父节点的索引
     * @param index
     * @return
     */
    private int parentIndex(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("index-0 don`t have parent.");
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