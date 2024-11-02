package com.cy.datastructure.test.onedimensional.linkedlist;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedArrayTest {

//    private LinkedListSingle<Integer> linkedListSingle;
    private LinkedListSingle<Integer> linkedListSingle;

    private int initnums = 8;

    @BeforeEach
    public void init() {
        linkedListSingle = new LinkedListSingle<>();
        for (int i = 0; i < initnums; i++) {
            linkedListSingle.add(i, i + 1);
        }
    }

    @Test
    void add() {
        // 期望值
        Comparable[] expect1 = {1,2,3,4,5,6,7,8};
        Assertions.assertTrue(linkedListSingle.getSize() == initnums);
        Assertions.assertFalse(linkedListSingle.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedListSingle.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addFirst() {
        // 期望值  {1,2,3,4,5,6,7,8}
        Comparable[] expect1 = {-1,0,1,2,3,4,5,6,7,8};
        linkedListSingle.addFirst(0);
        linkedListSingle.addFirst(-1);
        Assertions.assertTrue(linkedListSingle.getSize() == expect1.length);
        Assertions.assertFalse(linkedListSingle.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedListSingle.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addLast() {
        // 期望值 {1, 2, 3, 4, 5, 6, 7, 8}
        Comparable[] expect1 = {1,2,3,4,5,6,7,8,9,100};
        linkedListSingle.addLast(9);
        linkedListSingle.addLast(100);
        Assertions.assertTrue(linkedListSingle.getSize() == expect1.length);
        Assertions.assertFalse(linkedListSingle.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedListSingle.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void get_1() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 3;
        Comparable expect2 = 8;

        // 实际值
        Comparable actual1 = linkedListSingle.get(2);
        Comparable actual2 = linkedListSingle.get(linkedListSingle.getSize()-1);

        System.out.println(JSON.toJSONString(linkedListSingle.toArray()));
        Assertions.assertTrue(expect1 == actual1);
        Assertions.assertTrue(expect2 == actual2);
    }

    @Test
    void get_2() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 1;
        Comparable expect2 = 8;

        // 实际值
        Comparable actual1 = linkedListSingle.getFirst();
        Comparable actual2 = linkedListSingle.getLast();

        System.out.println(JSON.toJSONString(linkedListSingle.toArray()));
        Assertions.assertTrue(expect1 == actual1);
        Assertions.assertTrue(expect2 == actual2);
    }

    @Test
    void set_1() {
        // 期望值 {1, 2, 3, 4, 5, 6, 7, 8}
        Comparable expect1 = 30;
        Comparable expect2 = 88;
        Comparable[] expect3 = {1,2,30,4,5,6,7,88};

        // 实际值
        linkedListSingle.set(2,30);
        System.out.println(JSON.toJSONString(linkedListSingle.toArray()));
        Comparable actual1 = linkedListSingle.get(2);
        Assertions.assertTrue(expect1 == actual1);

        linkedListSingle.set(linkedListSingle.getSize()-1, 88);
        Comparable actual2 = linkedListSingle.get(linkedListSingle.getSize()-1);
        Assertions.assertTrue(expect2 == actual2);

        Comparable[] actual3 = linkedListSingle.toArray();
        Assertions.assertArrayEquals(actual3, expect3);
    }

    @Test
    void set_2() {
        for (int i = 0; i < initnums; i++) {
            linkedListSingle.removeLast();
        }
        Assertions.assertTrue(0 == linkedListSingle.getSize());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            linkedListSingle.set(0,222);
        });
    }

    @Test
    void contains() {
        Assertions.assertTrue(linkedListSingle.contain(3));
        Assertions.assertFalse(linkedListSingle.contain(100));
    }

    @Test
    void remove_1() {
        Comparable[] expect1 = {2,3,4,5,6,7};
        linkedListSingle.removeLast();
        linkedListSingle.removeFirst();
        Comparable[] actual1 = linkedListSingle.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void remove_2() {
        for (int i = 0; i < initnums; i++) {
            linkedListSingle.removeFirst();
        }
        Assertions.assertTrue(0 == linkedListSingle.getSize());
    }
}