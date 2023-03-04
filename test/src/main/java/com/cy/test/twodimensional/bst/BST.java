package com.cy.test.twodimensional.bst;

import com.cy.test.twodimensional.serviece.BSTService;

/**
 * @Author: Lil-K
 * @Date: 2023/3/3
 * @Description: 二分搜索树
 */
public class BST<E extends Comparable<E>> implements BSTService<E> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(){
            this(null,null,null);
        }

        public Node(E e){
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
        root = add_2(root, e);
    }

    private void add_1(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size ++;
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size ++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add_1(node.left, e);
        }else {
            add_1(node.right, e);
        }
    }

    /**
     * 不需要单独处理root节点的
     * @param node
     * @param e
     * @return
     */
    private Node add_2(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {// 放到左边
            node.left = add_2(node.left, e);
        } else if (e.compareTo(node.e) > 0) { // 放到右边
            node.right = add_2(node.right, e);
        }
        return node;
    }

    /**
     * 删除操作
     * 寻找前驱(左子树中最大的值) 或者 后继(右子树中最小的值)
     * @param e
     * @return
     */
    @Override
    public void remove(E e) {
        if (isEmpty()){
            throw new IllegalArgumentException("BST is empty!");
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
        } else {
            /**
             * 1. 如果左子树为空
             * 2. 如果右子树为空
             * 3. 如果左右都有子节点
             */
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            /**
             * 找到右子树中的后继
             */
//            Node successor = getMin(node.right);
//            successor.right = this.removeMin(node.right);
//            successor.left = node.left;
//            node.left = node.right = null;
//            return successor;

            /**
             * 找到左子树中的前驱
             */
            Node precursor = getMax(node.left);
            precursor.left = this.removeMax(node.left);
            precursor.right = node.right;
            node.left = node.right = null;
            return precursor;
        }
    }

    /**
     * 删除最小的元素
     * @return
     */
    @Override
    public E removeMin() {
        E min = getMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        /**
         * 向左走不动了
         * 如果右子树有值, 返回整个右子树
         */
        if (node.left == null) {
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除最大元素
     * @return
     */
    @Override
    public E removeMax() {
        E max = getMax();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if(node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    @Override
    public void set(E e) {

    }

    @Override
    public E getMax() {
        if (isEmpty()) {
            return null;
        }

        Node node = getMax(root);
        return node.e;
    }

    private Node getMax(Node node) {
        if (node.right == null) {
            return node;
        }
        return getMax(node.right);
    }

    @Override
    public E getMin() {
        if (isEmpty()) {
            return null;
        }
        Node node = getMin(root);
        return node.e;
    }

    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    @Override
    public boolean contain(E e) {
        return contains(root,e);
    }

    private boolean contains(Node node, E e){
        if (node == null) {
            return false;
        }

        if(e.compareTo(node.e) == 0){
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        }else {
            return contains(node.right, e);
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

    /** ========================= 前序遍历 preOrder ========================= **/
    public void preOrder() {
        this.preOrder(root);
    }

    private void preOrder(Node node) {
        if(node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }
    /** ============================= end ================================= **/


    /** ============================= 中序遍历 inOrder ========================= **/
    @Override
    public void inOrder() {
        this.inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }

        preOrder(node.left);
        System.out.println(node.e);
        preOrder(node.right);
    }
    /** ============================= end ============================= **/

    /** ========================= 后序遍历 endOrder ========================= **/
    @Override
    public void endOrder() {
        this.endOrder(root);
    }

    private void endOrder(Node node) {
        if(node == null) {
            return;
        }

        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.e);
    }
    /** ========================= end ========================= **/

    /** ========================= 层序遍历 levelOrder ========================= **/
    /**
     * 需要借助一个队列实现
     */
    @Override
    public void levelOrder() {

    }
    /** ========================= end ========================= **/


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
