package com.cy.datastructure.segmenttree.classes;

import com.cy.datastructure.segmenttree.interfaces.Rule;

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
  private Rule<E> rule;

  public SegmentTree(E[] arr, Rule<E> rule) {
    this.rule = rule;

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
      this.tree[treeIndex] = data[l];
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

    /** 创建左子树 **/
    buildSegmentTree(leftTreeIndex, l, mid);
    /** 创建右子树 **/
    buildSegmentTree(rightTreeIndex, mid + 1, r);

    /** 根节点的构建, 按照规则 "合并" 成根节点 **/
    this.tree[treeIndex] = rule.merge(this.tree[leftTreeIndex], this.tree[rightTreeIndex]);
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
    /**
     * 递归到底的情况
     * 当左边界与想要查找的左边界重合时
     * 当右边界与想要查找的右边界重合时
     */
    if(l == queryL && r == queryR) {
      return this.tree[treeIndex];
    }

    int mid = (r - l) / 2 + l;
    int leftTreeIndex = leftChild(treeIndex);
    int rightTreeIndex = rightChild(treeIndex);

    /**
     * 判断区间
     * 用户只关心【右】区间, 直接去【右】子树查找
     * 用户只关心【左】区间, 直接去【左】子树查找
     *
     * 最后返回 根节点就行了
     */
    if (queryL >= mid + 1) {
      return query(rightTreeIndex, mid + 1, r, queryL, queryR);
    } else if (queryR <= mid) {
      return query(leftTreeIndex, l, mid, queryL, queryR);
    }

    /**
     * 程序走到这里意味着:
     * 用户查找的区间 横跨了【左右两边】
     * -> 左区间需要单独查找
     * -> 右区间需要单独查找
     */
    E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
    E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);

    /** 组合区间的值 **/
    return rule.merge(leftRes, rightRes);
  }

  /**
   * 更新线段树中的节点值
   * 时间复杂度: O(logn)
   * @param index
   * @param e
   */
  public void update(int index, E e) {
    if (index < 0 || index >= data.length) {
      throw new IllegalArgumentException("Index is illegal");
    }

    /**
     * 先更新对应数组的值
     */
    data[index] = e;

    /**
     * 再维护线段树的性质
     * 比如父节点的merger 之后的值
     */
    update(0, 0, data.length - 1, index, e);
  }

  /**
   * 在以treeIndex为根的线段树中更新index的值为e
   * @param treeIndex
   * @param l
   * @param r
   * @param index
   * @param e
   */
  private void update(int treeIndex, int l, int r, int index, E e) {
    /**
     * 当左右边界相等的情况
     * 找到了
     */
    if (l == r) {
      this.tree[index] = e;
      return;
    }

    /**
     * 寻找 index 这个位置对应的叶子节点
     */
    int mid = l + (r - l) / 2;
    int leftTreeIndex = leftChild(treeIndex);
    int rightTreeIndex = rightChild(treeIndex);

    if (index >= mid + 1) {
      update(rightTreeIndex, mid + 1, r, index, e);
    }else {
      update(leftTreeIndex, l, mid, index, e);
    }

    /**
     * 【后序遍历】
     * 往上的所有父节点都会受到牵连
     * 最后需要更新父节点 聚合的结果
     */
    this.tree[treeIndex] = rule.merge(this.tree[leftTreeIndex], this.tree[rightTreeIndex]);
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
    for (int i = 0; i < this.tree.length; i++) {
      if (this.tree[i] != null) {
        res.append(this.tree[i]);
      }else {
        res.append("null");
      }

      if (i != this.tree.length - 1) {
        res.append(", ");
      }
    }
    res.append("]");

    return res.toString();
  }
}