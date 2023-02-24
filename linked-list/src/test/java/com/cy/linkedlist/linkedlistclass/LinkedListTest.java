package com.cy.linkedlist.linkedlistclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Assertions;

@Slf4j
class LinkedListTest {

    @Test
    public void addLast_1() {
        // 期望值
        Object[] expect = {2,4,21,1,10,10,10,10,100};

        // 实际值
        LinkedList<Object> list = new LinkedList<>();
        list.addLast(2);
        list.addLast(4);
        list.addLast(21);
        list.addLast(1);
        list.addLast(10);
        list.addLast(10);
        list.addLast(10);
        list.addLast(10);
        list.addLast(100);

        Object[] actualList = list.getLinkedList();

        log.info("expect value: {}", JSON.toJSONString(expect));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expect, actualList);
    }

    @Test
    void addFirst_1() {
        // 期望值
        Object[] expect = {12,20,24,2,4,21,1,10};

        // 实际值
        LinkedList<Object> list = new LinkedList<>();
        list.addFirst(10);
        list.addFirst(1);
        list.addFirst(21);
        list.addFirst(4);
        list.addFirst(2);
        list.addFirst(24);
        list.addFirst(20);
        list.addFirst(12);
        Object[] actualList = list.getLinkedList();

        log.info("expect value: {}", JSON.toJSONString(expect));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expect, actualList);
    }

    @Test
    void add() {
        // 期望值
        Object[] expect = {12,20,6,24,2};

        // 实际值
        LinkedList<Object> list = new LinkedList<>();
        list.addLast(12);
        list.addLast(20);
        list.addLast(24);
        list.addLast(2);
        list.add(6, 2);
        Object[] actualList = list.getLinkedList();

        log.info("expect value: {}", JSON.toJSONString(expect));
        log.info("actual value: {}", JSON.toJSONString(actualList));
        Assertions.assertArrayEquals(expect, actualList);
    }

    @Test
    void get() {
        // 期望值
        Object expect = 20;

        // 实际值
        LinkedList<Object> list = new LinkedList<>();
        list.addLast(12);
        list.addLast(20);
        list.addLast(24);
        list.addLast(2);

        Object actual = list.get(1);

        log.info("expect value: {}", JSON.toJSONString(expect));
        log.info("actual value: {}", JSON.toJSONString(actual));
        Assertions.assertEquals(expect, actual);
    }

    @Test
    void get_boundary() {
        // 期望值
        Object expect1 = 12;
        Object expect2 = 2;

        // 实际值
        LinkedList<Object> list = new LinkedList<>();
        list.addLast(12);
        list.addLast(20);
        list.addLast(24);
        list.addLast(2);

        Object actual1 = list.get(0);
        Object actual2 = list.get(list.getSize()-1);

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));

        Assertions.assertEquals(expect1, actual1);
        Assertions.assertEquals(expect2, actual2);
    }

    @Test
    void update() {
        // 期望值
        Object[] expect1 = {12,24,2,44};
        Object[] expect2 = {12,24,2,55};

        // 实际值
        LinkedList<Object> actualList = new LinkedList<>();
        actualList.addLast(12);
        actualList.addLast(24);
        actualList.addLast(2);
        actualList.addLast(44);
        Object[] actual1 = actualList.getLinkedList();
        Assertions.assertArrayEquals(expect1, actual1);

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));

        actualList.update(55, actualList.getSize()-1);
        Object[] actual2 = actualList.getLinkedList();

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2, actual2);

    }

    @Test
    void remove() {
        // 期望值
        Object[] expect1 = {12, 24, 2, 44, 555};
        Object[] expect2 = {12, 24, 44, 555};

        // 实际值
        LinkedList<Object> actualList = new LinkedList<>();
        actualList.addLast(12);
        actualList.addLast(24);
        actualList.addLast(2);
        actualList.addLast(44);
        actualList.addLast(555);
        Object[] actual1 = actualList.getLinkedList();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1, actual1);

        actualList.remove(2);
        Object[] actual2 = actualList.getLinkedList();

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2, actual2);

    }

    @Test
    void removeFirst() {
        // 期望值
        Object[] expect1 = {12, 24, 2, 44};
        Object[] expect2 = {24, 2, 44};

        // 实际值
        LinkedList<Object> actualList = new LinkedList<>();
        actualList.addLast(12);
        actualList.addLast(24);
        actualList.addLast(2);
        actualList.addLast(44);
        Object[] actual1 = actualList.getLinkedList();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1, actual1);

        actualList.removeFirst();
        Object[] actual2 = actualList.getLinkedList();

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2, actual2);
    }

    @Test
    void removeLast() {
        // 期望值
        Object[] expect1 = {12, 24, 2, 44};
        Object[] expect2 = {12, 24, 2};

        // 实际值
        LinkedList<Object> actualList = new LinkedList<>();
        actualList.addLast(12);
        actualList.addLast(24);
        actualList.addLast(2);
        actualList.addLast(44);
        Object[] actual1 = actualList.getLinkedList();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1, actual1);

        actualList.removeLast();
        Object[] actual2 = actualList.getLinkedList();

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2, actual2);
    }
}