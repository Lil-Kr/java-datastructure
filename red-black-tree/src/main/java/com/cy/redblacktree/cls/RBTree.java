package com.cy.redblacktree.cls;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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

        /**
         * 表示节点的颜色
         */
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
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
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

    /**
     * 删除元素
     * @return
     */
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
        return getNode(key) != null;
    }

    /**
     * 通过K 获取 V
     * @param key
     * @return
     */
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
    public void set(K key, V value) {
        Node newNode = getNode(key);
        if(Objects.isNull(newNode)) {
            throw new IllegalArgumentException(key + " doesn`t exist !");
        }
        newNode.value = value;
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
     *
     * @param node
     * @return
     */
    private Node rightRotate(Node node) {

        return null;
    }

}
