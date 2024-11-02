package com.cy.datastructure.datastructure.btree.avl.classes;

import com.cy.datastructure.datastructure.btree.avl.interfaces.AVLService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/30
 * @Description: Map 的结构
 */
public class AVLTree2<K extends Comparable<K>, V> implements AVLService<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public int height;

        public Node(K key, V value) {
            this(key, value,null,null,1);
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

    public AVLTree2() {
        this.root = null;
        this.size = 0;
    }

    /** ================ 辅助函数 ================ **/

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
     * 右旋转
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        /**
         * x 为 旋转之后的根节点
         */
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        /**
         * 维护高度height
         * 先更新 y 的 height, 再更新 x 的 height
         */
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * 左旋转
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        /**
         * x 为 旋转之后的根节点
         */
        Node x = y.right;
        Node t3 = x.left;

        x.left = y;
        y.right = t3;

        /**
         * 维护高度
         * 先维护y, 再维护x
         */
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0) {// 放左边
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {// 放右边
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        /**
         * 先维护当前 node 的 height, 高度
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        /** =========== 维护平衡操作 =========== **/

        /**
         * 左边比右边高, 并且 左右子树也是左高右低
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        /**
         * 右边比左边高的情况, 并且
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        /**
         * L-R 的情况
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        /**
         * R-L 的情况
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 删除过程中
     * @param key
     */
    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {// key.compareTo(node.key) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }else {
                Node s = getMin(node.right);
                s.right = remove(node.right, s.key);
                s.left = node.left;
                node.left = node.right = null;
                retNode = s;
            }
        }

        /**
         * 防止空指针,
         * 因为当删除节点之后, 剩下的部分是有可能为null的
         */
        if (retNode == null) {
            return null;
        }

        /**
         * 先维护当前 node 的 height, 高度
         */
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(retNode);

        /** =========== 维护平衡操作 =========== **/

        /**
         * 左边比右边高, 并且 左右子树也是左高右低
         */
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        /**
         * 右边比左边高的情况, 并且
         */
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        /**
         * L-R 的情况
         */
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        /**
         * R-L 的情况
         */
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return node;
    }

    private V getMin() {
        if (size == 0){
            throw new IllegalArgumentException("AVL tree is empty!");
        }
        return getMin(root).value;
    }

    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }

        return getMin(node.left);
    }

    @Override
    public boolean contain(K key) {
        return false;
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
        generateAVLString(root,0,res);

        return res.toString();
    }

    private void generateAVLString(Node node, int depth, StringBuilder res) {
        if(node == null) {
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

}