package com.cy.binarysearchtree.bstclass;

import com.cy.binarysearchtree.bstclass.interfaces.BSTService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: Lil-K
 * @Date: 2023/2/23
 * @Description: 二分搜索树
 */
public class BST<E extends Comparable<E>> implements BSTService<E> {

    private class Node {
        public E e;
        public Node left, right;

        public Node() {
            this(null,null,null);
        }

        public Node(E e) {
            this(e,null,null);
        }

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    private int size;

    private List<E> list;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 添加新元素
     * @param e
     */
    @Override
    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size ++;
//        }else {
//            add1(root, e);
//        }

        root = add2(root, e);
    }

    /**
     * add1() 这个方法需要单独处理根节点
     * 更推荐使用add2()
     * @param node
     * @param e
     */
    private void add1(Node node, E e) {
        /**
         * 终止条件
         */
        if (e.equals(node.e)){
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0){
            add1(node.left, e);
        }else{ // e.compareTo(node.e) > 0
            add1(node.right, e);
        }
    }

    /**
     * 此方法不需要单独处理根节点
     * @param node
     * @param e
     * @return
     */
    private Node add2(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = add2(node.left, e);
        }else if (e.compareTo(node.e) > 0){
            node.right = add2(node.right, e);
        }

        return node;
    }

    /**
     * 查找元素
     * @param e
     * @return
     */
    @Override
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if(node == null) {
            return false;
        }

        if(e.compareTo(node.e) == 0) {
            return true;
        }else if(e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }else {
            return contains(node.right, e);
        }
    }

    @Override
    public void update(E e) {
        // todo BST修改元素
    }

    /**
     * 删除最大值 -> 最左的节点
     * 删除最小值 -> 最右的节点
     * @param e
     * @return
     */
    @Override
    public void remove(E e) {
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }

        root = remove(root, e);
    }

    /**
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        /**
         * 寻找需要删除的位置
         */
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }else {// e == node.e

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
            }else {
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
     * 删除最小值, 并返回
     * @return
     */
    public E removeMin() {
        E ret = getMin();
        /**
         * 找到待删除元素的位置
         */
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
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

    public E removeMax() {
        E ret = getMax();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            // 保存左节点, 作为删除节点后新的二分搜索树的根
            Node leftNode = node.left;
            // 删除左节点
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /** =========================== 前序遍历 start ===========================**/
    /**
     * BST前序遍历
     * 以node为根的BST, 递归遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if(node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
    /** =========================== end ===========================**/


    /** =========================== 中序遍历 start ===========================**/
    public void inOrder() {
        inOrder(root);
        inOrderOutputList(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 输出中序遍历的结果
     */
    public void inOrderOutputList() {
        list = new ArrayList<>();
        inOrderOutputList(root);
    }

    private void inOrderOutputList(Node node) {
        if (node == null) {
            return;
        }

        inOrderOutputList(node.left);
        list.add(node.e);
        inOrderOutputList(node.right);
    }
    /** =========================== end ===========================**/

    /** =========================== 后序遍历 start ===========================**/
    /**
     * 后序遍历
     */
    public void endOrder() {
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
    /** =========================== end ===========================**/

    /** =========================== 层序遍历 start ===========================**/
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);

            if(cur.left != null) {
                q.add(cur.left);
            }
            if(cur.right != null) {
                q.add(cur.right);
            }
        }
    }
    /** =========================== end ===========================**/

    /** =========================== 获取最大值 ===========================**/
    public E getMax() {
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return getMax(root).e;
    }

    private Node getMax(Node node) {
        if (node.right == null){
            return node;
        }
        return getMax(node.right);
    }

    /**
     * 非递归实现, 获取最大值
     * @return
     */
    public E getMax1() {
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node max = new Node();

        while (!q.isEmpty()){
            Node cur = q.remove();

            if (cur.right != null){
                q.add(cur.right);
            }else {
                max = cur;
            }
        }
        return max.e;
    }
    /** =========================== end ===========================**/

    /** =========================== 获取最小值 ===========================**/
    public E getMin() {
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
        return getMin(root).e;
    }

    private Node getMin(Node node) {
        if(node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    /**
     * 获取最小值, 非递归写法
     * @return
     */
    public E getMin1() {
        if (size == 0){
            throw new IllegalArgumentException("BST is empty!");
        }
//        Queue<Node> q = new LinkedList<>();
//        q.add(root);
//        Node min = new Node();
//
//        while (!q.isEmpty()){
//            Node cur = q.remove();
//
//            if (cur.left != null){
//                q.add(cur.left);
//            }else {
//                min = cur;
//            }
//        }

        return getMin1(root).e;
    }

    private Node getMin1(Node node){
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        Node min = new Node();

        while (!q.isEmpty()){
            Node cur = q.remove();

            if (cur.left != null){
                q.add(cur.left);
            }else {
                min = cur;
            }
        }
        return min;
    }
    /** =========================== end ===========================**/

    public List getBSTListByInOrder(){
        this.inOrderOutputList();
        return list;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);

        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if(node == null) {
            res.append(generateDepthString(depth) + "null \n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}