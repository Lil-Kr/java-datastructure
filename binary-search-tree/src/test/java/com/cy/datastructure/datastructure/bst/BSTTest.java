package com.cy.datastructure.datastructure.bst;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class BSTTest {

    private static Comparable[] nums = {5,2,4,3,8,6};
    private static BST<Integer> bst;
    @BeforeEach
    public void init() {
        bst = new BST<>();
        for (Comparable num : nums) {
            bst.add((int)num);
        }
    }

    @Test
    public void add_success_1() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,3,6,8,4,2};
        int[] nums = {6,6,6,5,2,4,3,8,6};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);
    }

    @Test
    public void add_inOrder_1() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,3,6,8,4,2};
        Comparable[] nums = {5,2,4,3,8,6};
        for (Comparable num : nums) {
            bst.add((Integer) num);
        }

        bst.preOrder();
        System.out.println();
        bst.inOrder();
        System.out.println();
        bst.endOrder();
    }

    @Test
    public void preOrderNR() {
        BST<Integer> bst = new BST<>();
        Comparable[] nums = {5,2,4,3,8,6};
        for (Comparable num : nums) {
            bst.add((Integer) num);
        }

        bst.preOrderNR();
        System.out.println("\n");
        bst.preOrder();
    }

    @Test
    public void test_levelOrder_1() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,2,4,3,8,6};
        for (int num : nums) {
            bst.add(num);
        }
        bst.levelOrder();
    }

    @Test
    void getMax() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,2,4,3,8,6,1};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst.getMax());
    }

    @Test
    void getMin() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,2,4,3,8,6,1};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst.getMin());
    }

    @Test
    void getMin1() {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,2,4,3,8,6};
//        int[] nums = {0,5,2,4,3,8,6};
        for (int num : nums) {
            bst.add(num);
        }

        System.out.println(bst.getMin1());
    }

    @Test
    void getMax1() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,2,4,3,8,6};
        int[] nums = {5,2,4,3,8,6,10};
        for (int num : nums) {
            bst.add(num);
        }

        System.out.println(bst.getMax1());
    }

    @Test
    void removeMin() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,2,4,3,8,6};
        int[] nums = {5,2,4,3,8,6,10};
        for (int num : nums) {
            bst.add(num);
        }

        bst.inOrder();
        System.out.println("删除前");
        Integer ret = bst.removeMin();
        System.out.println("删除元素:" + ret);

        System.out.println("删除后");
        bst.inOrder();
    }

    @Test
    void removeMax() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,2,4,3,8,6};
        int[] nums = {5,2,4,3,8,6,10};
        for (int num : nums) {
            bst.add(num);
        }

        bst.inOrder();
        System.out.println("删除前");
        Integer ret = bst.removeMax();
        System.out.println("删除元素:" + ret);

        System.out.println("删除后");
        bst.inOrder();
    }

    @Test
    void remove() {
        BST<Integer> bst = new BST<>();
//        int[] nums = {5,2,4,3,8,6};
        int[] nums = {5,2,4,3,8,6,9};
        for (int num : nums) {
            bst.add(num);
        }
        System.out.println(bst);
        System.out.println("-----------------");

        bst.remove(8);
        System.out.println(bst);
        System.out.println("-----------------");
        bst.inOrder();
    }



}