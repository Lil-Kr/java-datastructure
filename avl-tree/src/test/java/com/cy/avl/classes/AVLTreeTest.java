package com.cy.avl.classes;

import com.cy.binarysearchtree.bstclass.BST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    private AVLTree<Integer,Object> avlTree;

    private BST<Integer> bst;

    @BeforeEach
    public void init() {
        avlTree = new AVLTree<>();
        bst = new BST<>();
    }

    @Test
    void add_1() {
        Integer[] nums = {1,2,3};
        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
            avlTree.add(nums[i], null);
        }

        System.out.println(bst);
        System.out.println("===========");
        System.out.println(avlTree);
    }

}