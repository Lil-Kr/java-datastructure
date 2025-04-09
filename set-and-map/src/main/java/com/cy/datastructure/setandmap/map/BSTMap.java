package com.cy.datastructure.setandmap.map;

import com.cy.datastructure.setandmap.service.MapService;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/2/24
 * @Description: 基于二分搜索树实现
 */
public class BSTMap<K extends Comparable<K>, V> implements MapService<K, V> {

  private class Node {
    public K key;
    public V value;
    public Node left, right;

    public Node(){
      this(null,null,null,null);
    }

    public Node(K key) {
      this(key, null,null,null);
    }

    public Node(K key,V value) {
      this(key, value,null,null);
    }

    public Node(K key, V value, Node left, Node right) {
      this.key = key;
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  private Node root;
  private int size;

  public BSTMap() {
    this.root = null;
    this.size = 0;
  }

  @Override
  public void add(K key, V value) {
    root = add(root, key, value);
  }

  /**
   * 以node为根的BST中插入元素(k, v), 返回插入新节点后BST的根
   * 递归写法
   * @param node
   * @param key
   * @param value
   * @return
   */
  private Node add(Node node, K key, V value){
    if (node == null){
      size++;
      return new Node(key, value);
    }

    if (key.compareTo(node.key) < 0){
      node.left = add(node.left, key,value);
    }else if (key.compareTo(node.key) > 0){
      node.right = add(node.right, key, value);
    }else { // key.compareTo(node.key) == 0
      node.value = value;
    }

    return node;
  }

  /**
   * 与BST 删除元素一致的操作
   * @param key
   * @return
   */
  @Override
  public V remove(K key) {
    if (isEmpty()){
      throw new IllegalArgumentException("BST is empty!");
    }

    Node del = getNode(key);
    if (del == null) {
      throw new IllegalArgumentException(key + " doesn`t exist !");
    }

    root = remove(root, key);
    return del.value;
  }

  private Node remove(Node node, K key) {
    if (node == null) {
      return null;
    }

    /**
     * 寻找需要删除的位置
     */
    if (key.compareTo(node.key) < 0) {// 操作左子节点, 寻找整颗左子树中的后继
      node.left = remove(node.left, key);
      return node;
    } else if (key.compareTo(node.key) > 0) {
      node.right = remove(node.right, key);
      return node;
    }else { // key.compareTo(node.key) == 0
      if (node.left == null) {
        Node rightNode = node.right;
        node.right = null;
        size --;
        return rightNode;
      } else if (node.right == null) {
        Node leftNode = node.left;
        node.left = null;
        size --;
        return leftNode;
      }else { //
        /**
         * 找到待删除元素【d】的后继
         * 也就是【d】的右子树中最小的元素, 用来替换【d】的位置
         */
        Node successor = getMin(node.right);
        successor.right = removeMin(node.right);// 已包含 size--
        successor.left = node.left;

        node.left = node.right = null; // 不需要再次 size--
        return successor;
      }
    }
  }

  public K removeMin() {
    K ret = getMin();
    /**
     * 找到待删除元素的位置
     */
    root = removeMin(root);
    return ret;
  }

  private Node removeMin(Node node) {
    if (node.left == null) {
      // 保存右节点, 作为删除节点后新的二分搜索树的根
      Node rightNode = node.right;
      // 删除右节点
      node.right = null;
      size--;
      return rightNode;
    }

    node.left = removeMin(node.left);
    return node;
  }

  public K getMin() {
    if (size == 0){
      throw new IllegalArgumentException("BSTMap is empty!");
    }
    return getMin(root).key;
  }

  private Node getMin(Node node) {
    if(node.left == null) {
      return node;
    }
    return getMin(node.left);
  }

  @Override
  public boolean contains(K key) {
    return getNode(key) != null;
  }

  /**
   * 通过K 获取 V
   * @param key
   * @return
   */
  @Override
  public V get(K key) {
    Node node = getNode(key);
    return node == null ? null : node.value;
  }

  private Node getNode(K key) {
    if(root == null) {
      return null;
    }

    Queue<Node> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      Node cur = q.remove();
      if (cur.key.equals(key)) {
        return cur;
      }

      if(cur.left != null) {
        q.add(cur.left);
      }
      if(cur.right != null) {
        q.add(cur.right);
      }
    }
    return null;
  }

  /**
   * 更新操作
   * @param key
   * @param value
   */
  @Override
  public void set(K key, V value) {
    Node newNode = getNode(key);
    if(Objects.isNull(newNode)) {
      throw new IllegalArgumentException(key + " doesn`t exist !");
    }
    newNode.value = value;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

}
