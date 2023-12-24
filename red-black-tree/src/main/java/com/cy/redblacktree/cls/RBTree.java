package com.cy.redblacktree.cls;

/**
 * @Author: Lil-K
 * @Date: 2023/11/13
 * @Description: 红黑树
 */
public class RBTree<K extends Comparable<K>, V> {

    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key,V value) {
            this(key, value,null,null);
        }

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            /**
             * 默认新节点与它的父节点在2-3树中是合并在一起的
             */
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 判断节点的颜色
     * @param node
     * @return
     */
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        /**
         * 保持最终的根节点为黑色节点
         */
        root.color = BLACK;
    }

    /**
     * 以node为根的RBTree中插入元素(k, v), 返回插入新节点后RBTree的根
     * 递归写法
     * @param node
     * @param key
     * @param value
     * @return 返回插入新节点红黑树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            // 返回一个红节点
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else { // key.compareTo(node.key) == 0
            node.value = value;
        }

        /**
         * 维护红黑树的性质
         */
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * 过去最小元素
     * @return
     */
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

    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    /**
     * 通过K 获取 V
     * @param key
     * @return
     */
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if(node == null) {
            return null;
        }

        if(key.equals(node.key)) {
            return node;
        } else if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else { // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    /**
     * 更新操作
     * @param key
     * @param newValue
     */
    public void set(K key, V newValue) {
        Node newNode = getNode(root, key);
        if(newNode == null) {
            throw new IllegalArgumentException(key + " doesn`t exist !");
        }
        newNode.value = newValue;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** ===========  辅助函数  ============**/

    /**
     * 左旋转操作
     * 返回左旋转之后, 新的node的根
     *
     *   node                     x
     *  /   \     左旋转         /  \
     * T1   x   --------->   node   T3
     *     / \              /   \
     *    T2 T3            T1   T2
     * @return new node
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;

        // 维护节点的颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 右旋转
     *
     *     node                   x
     *    /   \     右旋转       /  \
     *   x    T2   ------->   y   node
     *  / \                       /  \
     * y  T1                     T1  T2
     * @param node
     * @return new node
     */
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 颜色反转
     * @param node
     * @return
     */
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

}
