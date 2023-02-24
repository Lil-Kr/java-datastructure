package com.cy.setandmap.map.mapclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class LinkedListMapTest {

    private LinkedListMap<Object, Integer> linkedListMap;

    @BeforeEach
    public void init() {
        linkedListMap = new LinkedListMap<>();
    }

    @Test
    void add() {
        int[] nums = {5,2,4,3,8,6,6,3,0};
        for (int num : nums) {
            if (linkedListMap.contains(num)) {
                linkedListMap.set(num, linkedListMap.get(num) + 1);
            }else {
                linkedListMap.add(num,1);
            }
        }

        System.out.println(linkedListMap.getSize());
        System.out.println(linkedListMap.toString());
    }

    @Test
    void remove() {
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