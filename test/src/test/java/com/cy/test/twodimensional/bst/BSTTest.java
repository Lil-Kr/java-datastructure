package com.cy.test.twodimensional.bst;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class BSTTest {

    private BST<Integer> bst;

    private Integer initNums = 10;

    private Integer maxN = 322;
    private Integer minN = 0;

    private Integer[] nums = {4,22,28,9, minN,2,32,87, maxN};

    @BeforeEach
    public void init() {
        bst = new BST<>();
        for (Integer num : nums) {
            bst.add(num);
        }
    }

    @Test
    void add() {
        Assertions.assertTrue(nums.length == bst.getSize());
    }

    @Test
    void getMax() {
        Integer max = bst.getMax();
        Assertions.assertEquals(max,maxN);
    }

    @Test
    void getMin() {
        Integer min = bst.getMin();
        Assertions.assertEquals(min,minN);
    }

    @Test
    void contains() {
        boolean contain = bst.contain(maxN);
        Assertions.assertTrue(contain);

        boolean contain1 = bst.contain(minN);
        Assertions.assertTrue(contain1);

        boolean contain2 = bst.contain(100);
        Assertions.assertFalse(contain2);

        boolean contain3 = bst.contain(4);
        Assertions.assertTrue(contain3);

        boolean contain4 = bst.contain(28);
        Assertions.assertTrue(contain4);
    }

    /**
     * 测试删除 最大值和最小值
     */
    @Test
    void remove_1(){

        Integer min = bst.removeMin();
        Assertions.assertTrue(minN == min);
        Assertions.assertEquals(bst.getSize(), nums.length-1);

        Integer max = bst.removeMax();
        Assertions.assertTrue(maxN == max);
        Assertions.assertEquals(bst.getSize(), nums.length-2);
    }

    /**
     * 删除任意值
     */
    @Test
    void remove_2() {
        // {4,22,28,9, minN,2,32,87, maxN};
        System.out.println(bst);

        bst.remove(4);
        Assertions.assertEquals(bst.getSize(), nums.length-1);

        bst.remove(minN);
        Assertions.assertEquals(bst.getSize(), nums.length-2);

        bst.remove(maxN);
        Assertions.assertEquals(bst.getSize(), nums.length-3);

        System.out.println(bst);
    }
}