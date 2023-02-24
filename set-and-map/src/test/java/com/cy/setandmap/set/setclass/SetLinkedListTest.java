package com.cy.setandmap.set.setclass;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetLinkedListTest {

    private SetLinkedList<Integer> setLinkedList;

    @BeforeEach
    public void beforeEach() {
        setLinkedList = new SetLinkedList<>();
    }

    @Test
    void add() {
        int[] nums = {5,2,4,3,8,6,6,3,7};
        for (int num : nums) {
            setLinkedList.add(num);
        }
        System.out.println(JSON.toJSONString(setLinkedList.getSetBSTs()));
        System.out.println(setLinkedList.getSize());
    }

    @Test
    void remove() {
        int[] nums = {5,2,4,3,8,6,6,3,7};
        for (int num : nums) {
            setLinkedList.add(num);
        }
        System.out.println(setLinkedList.toString());

        System.out.println("移除元素后: ");

        setLinkedList.remove(6);
        setLinkedList.remove(3);
        System.out.println(setLinkedList.toString());
    }

    @Test
    void remove_2() {
        int[] nums = {3};
        for (int num : nums) {
            setLinkedList.add(num);
        }
        System.out.println(setLinkedList.toString());

        System.out.println("移除元素后: ");

//        setLinkedList.remove(6);
        setLinkedList.remove(3);
        System.out.println(setLinkedList.toString());
    }

    @Test
    void contains() {
    }
}