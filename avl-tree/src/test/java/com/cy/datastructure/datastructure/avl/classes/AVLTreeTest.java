package com.cy.datastructure.datastructure.avl.classes;

import com.cy.datastructure.datastructure.bst.BST;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

  private AVLTree<Integer, Object> avlTree;

  private BST<Integer> bst;

  @BeforeEach
  public void init() {
    avlTree = new AVLTree<>();
    bst = new BST<>();
  }

  @Test
  void add_1() {
    Integer[] nums = {1, 2, 3, 4, 5, 6};
    for (int i = 0; i < nums.length; i++) {
      avlTree.add(nums[i], null);
    }

    Assertions.assertEquals(nums.length, avlTree.getSize());

    System.out.println("===========");
    System.out.println(avlTree);


    avlTree.remove(6);
    avlTree.remove(5);
    avlTree.remove(4);

    Assertions.assertEquals(3, avlTree.getSize());

//        System.out.println("===========");
//        System.out.println(avlTree);
  }

}