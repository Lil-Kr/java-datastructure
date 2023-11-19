package com.cy.avl.classes;

import com.cy.avl.interfaces.AVLService;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil-K
 * @Date: 2023/3/21
 * @Description: AVL tree
 * 用Map的结构阐述, 在BST的基础上添加自平衡的机制
 *
 * 1. 设计
 */
public class AVLTree<K extends Comparable<K>, V> implements AVLService<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        /**
         * 每个节点的高度, height
         */
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
    @Override
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
        } else {
            node.value = value;
        }

        /**
         * 更新 height
         * 计算: 左右高度的最大值 + 1
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(node);

        /**
         * 不满足平衡二叉树的定义, 也就表明 不是一颗平衡二叉树
         */
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("un balanced: " + balanceFactor);
        }

        /** ================ 维护平衡性 ================ **/

        /**
         * 1. L-L的情况, balanceFactor > 1
         * 左边高于右边, 并且由于左侧的左侧多添加了一个节点导致不平衡的
         * 左边不平衡的情况 -> 需要右旋转
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {// 右旋转
            return rightRotate(node);
        }

        /**
         * 2. R-R的情况, balanceFactor < -1
         * 右边高于左边, 并且由于右侧的右侧多添加了一个节点导致不平衡的
         * 右侧不平衡的情况  -> 需要左旋转
         */
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {// 左旋转
            return leftRotate(node);
        }

        /**
         * LR -> LL -> balance
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
         * RL -> RR -> balance
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * 删除元素
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
        }else {
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
            } else {
                Node s = getMin(node.right);
                /**
                 * 此处remove 有可能会打破AVL的平衡
                 */
                s.right = remove(node.right, s.key); // 已包含 size--
                s.left = node.left;
                node.left = node.right = null; // 不需要再次 size--
                retNode = s;
            }
        }

        /**
         * 防止空指针
         * 因为当删除节点之后, 剩下的部分是有可能为null的
         */
        if (retNode == null) {
            return null;
        }

        /** ================ 维护平衡性 ================ **/

        /**
         * 更新 height
         * 计算: 左右高度的最大值 + 1
         */
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        /**
         * 计算平衡因子
         */
        int balanceFactor = getBalanceFactor(retNode);

        /**
         * 1. L-L的情况, balanceFactor > 1
         * 左边高于右边, 并且由于左侧的左侧多添加了一个节点导致不平衡的
         * 左边不平衡的情况 -> 需要右旋转
         */
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {// 右旋转
            return rightRotate(node);
        }

        /**
         * 2. R-R的情况, balanceFactor < -1
         * 右边高于左边, 并且由于右侧的右侧多添加了一个节点导致不平衡的
         * 右侧不平衡的情况  -> 需要左旋转
         */
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {// 左旋转
            return leftRotate(retNode);
        }

        /**
         * LR -> LL -> balance
         * 当前节点 -> 左子树 比 右子树高
         * 子节点 -> 左子树 比 右子树矮, 说明元素添加到了【左边的右边】
         */
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            /**
             * 先左旋转 -> 转化为LL的模式
             */
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        /**
         * RL -> RR -> balance
         */
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    /** =========================== 获取最小值 ===========================**/
    public V getMin() {
        if (size == 0) {
            throw new IllegalArgumentException("AVL is empty!");
        }
        return getMin(root).value;
    }

    private Node getMin(Node node) {
        if(node.left == null) {
            return node;
        }
        return getMin(node.left);
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
         * y.left 已变更为 x.right, y.right 不变 ()
         * x.left 不变, x.right 已变更为 y ()
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

    public static void main(String[] args) {
//        String s1 = new StringBuilder("go") .append("od").toString();
//        System.out.println(s1.intern() == s1);
//
//        String s2 = new StringBuilder("ja") .append("va").toString();
//        System.out.println(s2.intern() == s2);

        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2);
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

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contain(K key) {
        return false;
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