package com.cy.avl.classes;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/3/21
 * @Description: AVL tree
 * 用Map的结构阐述
 * 1. 在BST的基础上添加自平衡的机制
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        //
        public int height;
        public Node (K key, V value) {
            /**
             * height: 默认1
             * 当添加第一个元素时, 高度就是1了
             */
            this(key, value,null, null,1);
        }

        public Node(K key, V value, Node left, Node right, int height) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private Node root;

    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 添加节点
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else {
            node.value = value;
        }

        /**
         * 更新 height
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        /**
         * 不满足平衡二叉树的定义, 也就是不是一颗平衡二叉树
         */
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("un balanced: " + balanceFactor);
        }

        /** ================ 维护平衡性 ================ **/

        /**
         * 1. LL的情况, balanceFactor > 1, 左边高于右边, 左边不平衡的情况  -> 需要右旋转
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {// 右旋转
            return rightRotate(node);
        }

        /**
         * 维护平衡性
         * 2. RR的情况, balanceFactor < -1
         */
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {// 左旋转
            return leftRotate(node);
        }

        /**
         * LR
         * 当前节点 -> 左子树 比 右子树高
         * 子节点 -> 左子树 比 右子树矮, 说明元素添加到了【左边的右边】
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            /**
             * 先左旋转 -> 转化为LL的模式
             */
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        /**
         * RL
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 右旋转
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
         */
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
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
     * 判断该二叉树是否是一颗平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) >= 2) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 判断当前的BST是否是一颗二分搜索树
     * 利用中序遍历(遍历之后是按升序排列的)
     */
    public boolean isBST () {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        if (CollectionUtils.isEmpty(keys)) {
            return false;
        }

        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 计算平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 获得节点的高度
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}