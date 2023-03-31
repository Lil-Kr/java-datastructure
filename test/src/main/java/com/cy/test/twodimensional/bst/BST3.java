package com.cy.test.twodimensional.bst;

import com.cy.test.twodimensional.serviece.BSTService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/31
 * @Description:
 * * 添加操作
 * * 删除操作
 * * 查询
 * * 获取元素
 */
public class BST3<E extends Comparable<E>> implements BSTService<E> {

    private class Node {
        public E e;
        public Node left, right;

        public Node (E e) {
            this(e, null, null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    private int size;

    public BST3 () {
        root = null;
        size = 0;
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    @Override
    public E getMin() {
        if (isEmpty()) {
            return null;
        }
        Node min = getMin(root);
        return min.e;
    }

    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }

        return getMin(node.left);
    }

    @Override
    public E getMax() {
        if (isEmpty()) {
            return null;
        }
        Node max = getMax(root);
        return max.e;
    }

    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    @Override
    public void remove(E e) {
        if (isEmpty()) {
            return;
        }
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }else { // e.compareTo(node.e) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else {// left and right both have node
                Node s = getMin(node.right);
                s.right = removeMin(node.right);
                s.left = node.left;
                node.left = node.right = null; // help for gc
                return s;
            }
        }
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            return null;
        }
        E min = getMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public E removeMax() {
        if (isEmpty()) {
            return null;
        }
        E max = getMax();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public void set(E oldV, E newV) {
        if (isEmpty() || !contain(oldV)) {
            return;
        }

        remove(oldV);
        add(newV);
    }

    @Override
    public boolean contain(E e) {
        if (isEmpty()) {
            return false;
        }

        return contain(root, e);
    }

    private boolean contain(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contain(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contain(node.right, e);
        }else {
            return true;
        }
    }

    @Override
    public void preOrder() {

    }

    @Override
    public void preOrderNR() {

    }

    @Override
    public void inOrder() {

    }

    @Override
    public void endOrder() {

    }

    @Override
    public void levelOrder() {

    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
