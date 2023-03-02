package com.cy.linkedlist.linkedlistclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedArrayTest {

    private LinkedListDummy<Integer> linkedArray;

    private int initnums = 8;

    @BeforeEach
    public void init() {
        linkedArray = new LinkedListDummy<>();
        for (int i = 0; i < initnums; i++) {
            linkedArray.add(i, i + 1);
        }
    }

    @Test
    void add() {
        // 期望值
        Comparable[] expect1 = {1,2,3,4,5,6,7,8};
        Assertions.assertTrue(linkedArray.getSize() == initnums);
        Assertions.assertFalse(linkedArray.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedArray.toArray();

        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addFirst() {
        // 期望值  {1,2,3,4,5,6,7,8}
        Comparable[] expect1 = {-1,0,1,2,3,4,5,6,7,8};
        linkedArray.addFirst(0);
        linkedArray.addFirst(-1);
        Assertions.assertTrue(linkedArray.getSize() == expect1.length);
        Assertions.assertFalse(linkedArray.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedArray.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void addLast() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable[] expect1 = {1,2,3,4,5,6,7,8,9,100};
        linkedArray.addLast(9);
        linkedArray.addLast(100);
        Assertions.assertTrue(linkedArray.getSize() == expect1.length);
        Assertions.assertFalse(linkedArray.isEmpty());

        // 实际值
        Comparable[] actual1 = linkedArray.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void get_1() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 3;
        Comparable expect2 = 8;

        // 实际值
        Comparable actual1 = linkedArray.get(2);
        Comparable actual2 = linkedArray.get(linkedArray.getSize()-1);

        System.out.println(linkedArray.toArray());
        Assertions.assertTrue(expect1==actual1);
        Assertions.assertTrue(expect2==actual2);
    }

    @Test
    void set_1() {
        // 期望值 {1,2,3,4,5,6,7,8}
        Comparable expect1 = 30;
        Comparable expect2 = 88;
        Comparable[] expect3 = {1,2,30,4,5,6,7,88};

        // 实际值
        linkedArray.set(2,30);
        System.out.println(linkedArray.toArray());
        Comparable actual1 = linkedArray.get(2);
        Assertions.assertTrue(expect1 == actual1);

        linkedArray.set(linkedArray.getSize()-1, 88);
        Comparable actual2 = linkedArray.get(linkedArray.getSize()-1);
        Assertions.assertTrue(expect2 == actual2);

        Comparable[] actual3 = linkedArray.toArray();
        Assertions.assertArrayEquals(actual3, expect3);
    }

    @Test
    void set_2() {
        for (int i = 0; i < initnums; i++) {
            linkedArray.removeLast();
        }

        Assertions.assertTrue(0 == linkedArray.getSize());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            linkedArray.set(0,222);
        });
    }

    @Test
    void contains() {
        Assertions.assertTrue(linkedArray.contain(3));
        Assertions.assertFalse(linkedArray.contain(100));
    }

    @Test
    void remove_1() {
        Comparable[] expect1 = {2,3,4,5,6,7};
        linkedArray.removeLast();
        linkedArray.removeFirst();
        Comparable[] actual1 = linkedArray.toArray();
        Assertions.assertArrayEquals(expect1, actual1);
    }

    @Test
    void remove_2() {
        for (int i = 0; i < initnums; i++) {
            linkedArray.removeFirst();
        }
        Assertions.assertTrue(0 == linkedArray.getSize());
    }
}