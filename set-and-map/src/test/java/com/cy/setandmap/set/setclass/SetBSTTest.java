package com.cy.setandmap.set.setclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SetBSTTest {

    private SetBST<Integer> setBST;

    @BeforeEach
    public void beforeEach() {
        setBST = new SetBST<>();
    }

    @Test
    void add() {
        int[] nums = {5,2,4,3,8,6,6,3};
        for (int num : nums) {
            setBST.add(num);
        }
        System.out.println(setBST.toString());
    }

    @Test
    void remove() {
        int[] nums = {5,2,4,3,8,6,6,3};
        for (int num : nums) {
            setBST.add(num);
        }
        log.info("原数组: \n{}", setBST.getSetBSTs());

        setBST.remove(8);
        log.info("删除元素后: \n{}", setBST.getSetBSTs());
    }

    @Test
    void contains() {
        int[] nums = {5,2,4,3,8,6,6,3};
        for (int num : nums) {
            setBST.add(num);
        }

        Assertions.assertFalse(setBST.contains(66));
        Assertions.assertTrue(setBST.contains(3));
    }

    @Test
    void getSize() {
    }

    @Test
    void isEmpty() {
    }
}