package com.cy.test.twodimensional.segmenttree;

/**
 * @Author: Lil-K
 * @Date: 2023/8/20
 * @Description:
 *  * 使用数组表示线段树(represented in the form of an array)
 *  * 以求和为例, 本例子中线段树的作用是用来求和
 */
public class SegmentTree<E> implements SegmentTreeService<E> {

    private E[] data;

    private E[] tree;

    private Merger merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        /**
         * as a tree structure, the tree length need 4 times that data length
         */
        tree = (E[]) new Object[arr.length << 2];


        buildSegmentTree(0,0, data.length - 1);
    }

    /**
     * 在 treeIndex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChildIndex(treeIndex);
        int rightTreeIndex = rightChildIndex(treeIndex);

        // 计算中间位置
        int mid = l + (r - l) / 2;

        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        /**
         * 此处才是真正的业务逻辑, 综合左右两个子节点的信息, 做一些业务逻辑
         * 这个例子是做求和, 所以是左右节点相加
         */
        tree[treeIndex] = (E) merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    /**
     * 返回区间 [queryL, queryR]的值
     * @param queryL 有效的查询左区间
     * @param queryR 有效的查询右区间
     * @return
     */
    @Override
    public E query(int queryL, int queryR) {
        this.checkRangeIndex(queryL, queryR);
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeID为根的线段树中[l...r]的范围里, 搜索区间[qL....qR]的值
     *
     * @param treeIndex 线段树中某一个根节点
     * @param l 某一个根节点的左边界
     * @param r 某一个根节点的右边界
     * @param qL
     * @param qR
     * @return
     */
    private E query(int treeIndex, int l, int r, int qL, int qR) {
        /**
         * 当查询的边界与线段树的边界重合时, 就是用户想要的信息
         */
        if (l == qL && r ==qR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChildIndex(treeIndex);
        int rightTreeIndex = rightChildIndex(treeIndex);

        /**
         * 判断查询范围的左边界是否在当前线段树中间位置,
         *  * 如果超过中间位置, 说明待查询的左边界在当前线段树中间位置之前不存在, 减小查询范围, qL >= mid + 1
         *  * 右边界同理, qR <= mid
         */
        if (qL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, qL,qR);
        } else if (qR <= mid) {
            return query(leftTreeIndex, l, mid, qL, qR);
        }

        /**
         * 如果上面的判断都不符合, 还有一种情况就是 待查询的区间左右两边范围均有涉及
         */
        E queryL = query(leftTreeIndex, l, mid, qL, mid);
        E queryR = query(rightTreeIndex, mid + 1, r, mid + 1, qR);

        return (E) merger.merge(queryL, queryR);
    }


    @Override
    public E get(int index) {
        this.checkIndex(index);
        return data[index];
    }

    @Override
    public Boolean isEmpty() {
        return data.length == 0;
    }

    @Override
    public int getSize() {
        return data.length;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }

            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is Illegal");
        }
    }

    private void checkRangeIndex(int l, int r) {
        if (l < 0 || l >= data.length || r < 0 || r >= data.length || l > r) {
            throw new IllegalArgumentException("Index is illegal");
        }
    }
}