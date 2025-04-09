package com.cy.datastructure.test.twodimensional.avl;

import com.cy.datastructure.test.twodimensional.serviece.AVLService;

/**
 * @Author: Lil-K
 * @Date: 2023/4/1
 * @Description: 实现avl树
 *
 * * 基于Map结构
 * * 节点之间的高度维护
 * * 平衡因子的计算
 * * 平衡的维护(LL, RR, L-R, R-L)
 */
public class AVT01<K extends Comparable<K>, V> implements AVLService<K, V> {

  private Node root;
  private int size;
  public AVT01() {
    this.root = null;
    this.size = 0;
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return node.height;
  }

  private int getBalanceFactor(Node node) {
    if (node == null) {
      return 0;
    }
    return getHeight(node.left) - getHeight(node.right);
  }

  /**
   * 左旋
   *
   * @param y
   * @return
   */
  private Node leftRotate(Node y) {
    Node x = y.right;
    Node t2 = x.left;

    /**
     * 向左旋转过程
     */
    x.left = y;
    y.right = t2;

    /**
     * 更新 height
     */
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    return x;
  }

  /**
   * 右旋
   *
   * @param y
   * @return
   */
  private Node rightRotate(Node y) {
    Node x = y.left;
    Node t3 = x.right;

    /**
     * 向右旋转的过程
     */
    x.right = y;
    y.left = t3;

    /**
     * 更新 height
     * y.left 已变更为 x.right, y.right 不变 ()
     * x.left 不变, x.right 已变更为 y ()
     */
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    return x;
  }

  @Override
  public void add(K key, V value) {
    root = add(root, key, value);
  }

  private Node add(Node node, K key, V value) {
    if (node == null) {
      size++;
      return new Node(key, value);
    }

    if (key.compareTo(node.key) < 0) {
      node.left = add(node.left, key, value);
    } else if (key.compareTo(node.key) > 0) {
      node.right = add(node.right, key, value);
    } else {
      node.value = value;
    }

    /**
     * 通过回溯, 维护当前节点的 height
     */
    node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

    /**
     * 计算当前节点的平衡因子
     */
    int balanceFactor = getBalanceFactor(node);

    /** =========== 维护AVL的平衡性 =========== **/

    /**
     * L-L:
     * R-R:
     * L-R:
     * R-L:
     * **/
    if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
      return rightRotate(node);
    }

    if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
      return leftRotate(node);
    }

    if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }

    if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }

    return node;
  }

  @Override
  public void remove(K key) {
    if (isEmpty()) {
      return;
    }
    root = remove(root, key);
  }

  private Node remove(Node node, K key) {
    if (node == null) {
      return null;
    }

    Node ret;
    if (key.compareTo(node.key) < 0) {
      node.left = remove(node.left, key);
      ret = node;
    } else if (key.compareTo(node.key) > 0) {
      node.right = remove(node.right, key);
      ret = node;
    } else {
      if (node.left == null) {
        Node rightNode = node.right;
        node.right = null;
        size--;
        ret = rightNode;
      } else if (node.right == null) {
        Node leftNode = node.left;
        node.left = null;
        size--;
        ret = leftNode;
      } else {
        Node s = getMin(node.right);
        s.right = remove(node.right, s.key);
        s.left = node.left;
        node.left = node.right = null;
        ret = s;
      }
    }

    if (ret == null) {
      return null;
    }

    /**
     * 通过回溯, 维护当前节点的 height
     */
    ret.height = 1 + Math.max(getHeight(ret.left), getHeight(ret.right));

    /**
     * 计算当前节点的平衡因子
     */
    int balanceFactor = getBalanceFactor(ret);

    /** =========== 维护平衡操作 =========== **/

    /**
     * 左边比右边高, 并且 左右子树也是左高右低
     */
    if (balanceFactor > 1 && getBalanceFactor(ret.left) >= 0) {
      return rightRotate(ret);
    }

    /**
     * 右边比左边高的情况, 并且
     */
    if (balanceFactor < -1 && getBalanceFactor(ret.right) <= 0) {
      return leftRotate(ret);
    }

    /**
     * L-R 的情况
     */
    if (balanceFactor > 1 && getBalanceFactor(ret.left) < 0) {
      ret.left = leftRotate(ret.left);
      return rightRotate(ret);
    }

    /**
     * R-L 的情况
     */
    if (balanceFactor < -1 && getBalanceFactor(ret.right) > 0) {
      ret.right = rightRotate(ret.right);
      return leftRotate(ret);
    }

    return ret;
  }

  @Override
  public K getMin() {
    if (isEmpty()) {
      return null;
    }

    return getMin(root).key;
  }

  private Node getMin(Node node) {
    if (node.left == null) {
      return node;
    }

    return getMin(node.left);
  }

  @Override
  public K getMax() {
    return null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    generateAVLString(root, 0, res);

    return res.toString();
  }

  private void generateAVLString(Node node, int depth, StringBuilder res) {
    if (node == null) {
      res.append(generateDepthString(depth) + "null \n");
      return;
    }

    res.append(generateDepthString(depth) + node.key + "\n");
    generateAVLString(node.left, depth + 1, res);
    generateAVLString(node.right, depth + 1, res);
  }

  private String generateDepthString(int depth) {
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < depth; i++) {
      res.append("--");
    }
    return res.toString();
  }

  private class Node {
    public K key;
    public V value;
    public Node left, right;

    public int height;

    public Node(K key, V value) {
      this(key, value, null, null, 1);
    }

    public Node(K key, V value, Node left, Node right, int height) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
      this.height = height;
    }
  }

}
