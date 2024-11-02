package com.cy.datastructure.setandmap.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedListMapTest {

    private LinkedListMap<Integer, Integer> linkedListMap;

    @BeforeEach
    public void init() {
        linkedListMap = new LinkedListMap<>();
    }

    @Test
    void add_success_1() {
        // 期望值
        int exceptSize = 7;

        // 实际值
        int[] nums = {5,5,2,4,3,8,6,6,3,0,0};
        for (int num : nums) {
            if (linkedListMap.contains(num)) {
                linkedListMap.set(num, linkedListMap.get(num) + 1);
            }else {
                linkedListMap.add(num,1);
            }
        }

        Assertions.assertEquals(exceptSize,linkedListMap.getSize());
        System.out.println(linkedListMap.toString());
    }

    @Test
    void remove_success_1() {
        // 期望值
        int exceptSize1 = 7;
        int exceptSize2 = 6;

        // 实际值
        int[] nums = {5,5,2,4,3,8,6,6,3,0,0};
        for (int num : nums) {
            if (linkedListMap.contains(num)) {
                linkedListMap.set(num, linkedListMap.get(num) + 1);
            }else {
                linkedListMap.add(num,1);
            }
        }
        Assertions.assertEquals(exceptSize1,linkedListMap.getSize());
        System.out.println(linkedListMap.toString());

        System.out.println("删除后");

        Integer remove = linkedListMap.remove(0);
        Assertions.assertEquals(exceptSize2,linkedListMap.getSize());
        System.out.println(linkedListMap.toString());
    }

    @Test
    void contains() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }
}