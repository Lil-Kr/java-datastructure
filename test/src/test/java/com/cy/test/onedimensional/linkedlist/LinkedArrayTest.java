package com.cy.test.onedimensional.linkedlist;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedArrayTest {

    private LinkedList<Integer> linkedList;

    private int initnums = 8;

    @BeforeEach
    public void init() {
        linkedList = new LinkedList<>();
        for (int i = 0; i < initnums; i++) {
            linkedList.add(i, i + 1);
        }
    }

    @Test
    void add() {
        // 期望值
        Comparable[] expect1 = {1,2,3,4,5,6,7,8};
        Assertions.assertTrue(linkedList.getSize() == initnums);
        Assertions.assertFalse(linkedList.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedList.toArray();

        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addFirst() {
        // 期望值  {1,2,3,4,5,6,7,8}
        Comparable[] expect1 = {-1,0,1,2,3,4,5,6,7,8};
        linkedList.addFirst(0);
        linkedList.addFirst(-1);
        Assertions.assertTrue(linkedList.getSize() == expect1.length);
        Assertions.assertFalse(linkedList.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedList.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addLast() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable[] expect1 = {1,2,3,4,5,6,7,8,9,100};
        linkedList.addLast(9);
        linkedList.addLast(100);
        Assertions.assertTrue(linkedList.getSize() == expect1.length);
        Assertions.assertFalse(linkedList.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedList.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void get_1() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 3;
        Comparable expect2 = 8;

        // 实际值
        Comparable actual1 = linkedList.get(2);
        Comparable actual2 = linkedList.get(linkedList.getSize()-1);

        System.out.println(linkedList.toArray());
        Assertions.assertTrue(expect1 == actual1);
        Assertions.assertTrue(expect2 == actual2);
    }

    @Test
    void set_1() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 30;
        Comparable expect2 = 88;
        Comparable[] expect3 = {1,2,30,4,5,6,7,88};

        // 实际值
        linkedList.set(2,30);
        System.out.println(linkedList.toArray());
        Comparable actual1 = linkedList.get(2);
        Assertions.assertTrue(expect1 == actual1);

        linkedList.set(linkedList.getSize()-1, 88);
        Comparable actual2 = linkedList.get(linkedList.getSize()-1);
        Assertions.assertTrue(expect2 == actual2);

        Comparable[] actual3 = linkedList.toArray();
        Assertions.assertArrayEquals(actual3, expect3);
    }

    @Test
    void set_2() {
        for (int i = 0; i < initnums; i++) {
            linkedList.removeLast();
        }

        Assertions.assertTrue(0 == linkedList.getSize());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            linkedList.set(0,222);
        });
    }

    @Test
    void contains() {
        Assertions.assertTrue(linkedList.contain(3));
        Assertions.assertFalse(linkedList.contain(100));
    }

    @Test
    void remove_1() {
        Comparable[] expect1 = {2,3,4,5,6,7};
        linkedList.removeLast();
        linkedList.removeFirst();
        Comparable[] actual1 = linkedList.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void remove_2() {
        for (int i = 0; i < initnums; i++) {
            linkedList.removeFirst();
        }
        Assertions.assertTrue(0 == linkedList.getSize());
    }
}