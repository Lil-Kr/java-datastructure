package com.cy.test.twodimensional.avl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

    private AVT01<Integer,Object> avlTree;

    @BeforeEach
    public void init() {
        avlTree = new AVT01<>();
    }

    @Test
    void add_1() {
        Integer[] nums = {1,2,3,4,5,6};
        for (int i = 0; i < nums.length; i++) {
            avlTree.add(nums[i], null);
        }

        Assertions.assertEquals(nums.length, avlTree.getSize());

        System.out.println("===========");
        System.out.println(avlTree);

    }

    @Test
    void remove_1() {
        Integer[] nums = {1,2,3,4,5,6};
        for (int i = 0; i < nums.length; i++) {
            avlTree.add(nums[i], null);
        }

        avlTree.remove(6);
        avlTree.remove(5);
        avlTree.remove(4);

        Assertions.assertEquals(3, avlTree.getSize());
    }

}