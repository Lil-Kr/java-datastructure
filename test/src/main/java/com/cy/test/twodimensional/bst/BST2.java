package com.cy.test.twodimensional.bst;

import com.cy.test.twodimensional.serviece.BSTService;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author: Lil-K
 * @Date: 2023/3/12
 * @Description:
 */
public class BST2<E extends Comparable<E>> implements BSTService<E> {

    private class Node{
        public E e;
        public Node left,right;

        public Node(E e) {
            this(e,null,null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    private int size;

    private Node root;

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
            node.left = add(node.left,e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right,e);
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

        root = remove(root,e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
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
            }else {
                Node s = getMin(node.right);
                s.right = removeMin(node.right);
                s.left = node.left;
                node.left = node.right = null;
                return s;
            }
        }
    }

    @Override
    public E removeMin() {
        if (isEmpty()) {
            return null;
        }

        Node min = getMin(root);
        root = removeMin(root);
        return min.e;
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

        Node max = getMax(root);
        root = removeMax(root);
        return max.e;
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
        if (isEmpty()) {
            return;
        }
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void preOrderNR() {
        if (isEmpty()) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
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

        endOrder(node.left);
        endOrder(node.right);
        System.out.println(node.e);
    }

    @Override
    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
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
