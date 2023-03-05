package com.cy.test.twodimensional.bst;

import com.cy.test.twodimensional.serviece.BSTService;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/3/5
 * @Description:
 */
public class BST<E extends Comparable<E>> implements BSTService<E> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = this.right = null;
        }
    }

    private int size;

    private Node root;

    public BST() {
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
    public void remove(E e) {
        if (isEmpty())
            throw new IllegalArgumentException("bst remove method is error, the bst is empty");

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
                size --;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            } else {// 既有左子节点, 又有右子节点
                /**
                 * 寻找一个后继, 或者前驱来替换待删除的节点
                 * 并返回替换后的根
                 */
                Node successor = getMin(node.right);
                successor.right = removeMin(node.right);

                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    @Override
    public E removeMin() {
        if (isEmpty())
            return null;

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
        if (isEmpty())
            return null;

        E max = getMax();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        /**
         * 递归到底
         */
        if (node.right == null) {
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }

        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public E getMax() {
        if (isEmpty())
            return null;

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
    public E getMin() {
        if (isEmpty())
            return null;

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
    public void set(E oldV, E newV) {
        if (isEmpty()) {
            return;
        }

        /**
         * 只需要删除
         */
        this.remove(oldV);
        this.add(newV);
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

        if(e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contain(node.left, e);
        }else {
            return contain(node.right, e);
        }

    }

    @Override
    public void preOrder() {
        if (isEmpty()) {
            return;
        }

        preOrder(root);
    }

    private void preOrder(Node node) {
        /**
         * 递归到底,
         * 先左子节点到底
         * 然后从左子节点返回上一级, 再访问右子节点
         */
        if(node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void inOrder() {
        if (isEmpty()) {
            return;
        }

        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    @Override
    public void endOrder() {
        if (isEmpty()) {
            return;
        }
        endOrder(root);
    }

    private void endOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public void levelOrder() {
        if (isEmpty()) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
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
