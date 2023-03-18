package com.cy.segmenttree.classes;

import com.cy.segmenttree.interfaces.Merger;

/**
 * @Author: Lil-K
 * @Date: 2023/3/17
 * @Description: 线段树结构实现
 */
public class SegmentTree<E> {

    // 存储线段树结构的数组
    private E[] tree;

    // 存储真实的数据
    private E[] data;

    // 规则 interface
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;

        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        this.tree = (E[]) new Object[4 * arr.length];

        /**
         * 构建线段树
         */
        buildSegmentTree(0, 0, this.data.length - 1);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 在treeIndex 的位置创建表示区间 [l ... r] 的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        /**
         * 递归到底:
         * 不能再拆分的情况下, 这个根节点就是它本身, 上一级节点的值就是它本身
         */
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        /**
         * 计算每个节点 -> 左右子节点
         */
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        /**
         * 计算中间位置的索引 [防止整型溢出]
         * (l + r) /2; -> 当 l 或者 r 很大的时候, 可能有整数溢出的情况
         */
        int mid = l + (r - l) / 2 ;

        // 创建左子树
        buildSegmentTree(leftTreeIndex, l, mid);
        // 创建右子树
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        // 根节点的构建, 按照规则
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 查询区间 [queryL, queryR]
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length
                || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在一treeId为根的线段树中, [l...r]的范围里, 搜索区间[queryL....queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if(l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = (r - l)/2 + l;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 用户关心的左区间与
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {//
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
        E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftRes, rightRes);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            }else {
                res.append("null");
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");

        return res.toString();
    }
}