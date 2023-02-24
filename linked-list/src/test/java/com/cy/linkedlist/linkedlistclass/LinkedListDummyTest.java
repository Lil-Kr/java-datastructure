package com.cy.linkedlist.linkedlistclass;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class LinkedListDummyTest {

    @Test
    void add() {
        // 期望值
        Object[] expect1 = {4, 3, 33, 78};
        Object[] expect2 = {4, 3, 6, 33, 78};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(4);
        listDummy.addLast(3);
        listDummy.addLast(33);
        listDummy.addLast(78);
        Object[] actual1 = listDummy.getArray();
        Assertions.assertArrayEquals(expect1,actual1);

        listDummy.add(2,6);
        Object[] actual2 = listDummy.getArray();
        Assertions.assertArrayEquals(expect2,actual2);

        log.info(listDummy.toString());
    }

    @Test
    void addFirst() {
        // 期望值
        Object[] expect1 = {78,33,3,4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addFirst(4);
        listDummy.addFirst(3);
        listDummy.addFirst(33);
        listDummy.addFirst(78);
        Object[] actual1 = listDummy.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);
    }

    @Test
    void addLast() {
        // 期望值
        Object[] expect1 = {78,33,3,4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);
    }

    @Test
    void update() {
        // 期望值
        Object[] expect1 = {78, 33, 3, 4};
        Object[] expect2 = {78, 33, 21, 4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();
        listDummy.update(2, 21);

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        Object[] actual2 = listDummy.getArray();

        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));

        Assertions.assertArrayEquals(expect2,actual2);
    }

    @Test
    void remove() {
        // 期望值
        Object[] expect1 = {78, 33, 3, 4};
        Object[] expect2 = {78, 3, 4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        listDummy.remove(1);
        Object[] actual2 = listDummy.getArray();
        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2,actual2);
    }

    @Test
    void removeFirst() {
        // 期望值
        Object[] expect1 = {78, 33, 3, 4};
        Object[] expect2 = {33, 3, 4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        listDummy.removeFirst();
        Object[] actual2 = listDummy.getArray();
        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2,actual2);
    }

    @Test
    void removeLast() {
        // 期望值
        Object[] expect1 = {78, 33, 3, 4};
        Object[] expect2 = {78, 33, 3};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        listDummy.removeLast();
        Object[] actual2 = listDummy.getArray();
        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2,actual2);
    }

    @Test
    void get() {
        // 期望值
        Object[] expect1 = {78, 33, 3, 4};

        // 实际值
        LinkedListDummy<Integer> listDummy = new LinkedListDummy<>();
        listDummy.addLast(78);
        listDummy.addLast(33);
        listDummy.addLast(3);
        listDummy.addLast(4);
        Object[] actual1 = listDummy.getArray();


        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        Integer integer = listDummy.get(0);
        Assertions.assertEquals(integer,78);

        Integer integer2 = listDummy.get(listDummy.getSize() - 1);
        Assertions.assertEquals(integer2,4);

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> listDummy.get(-1));
        String expectedMessage = "dummyHead linkedlist add method index was error";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        Exception exception2 = Assertions.assertThrows(IllegalArgumentException.class, () -> listDummy.get(listDummy.getSize()));
        String expectedMessage2 = "dummyHead linkedlist add method index was error";
        String actualMessage2 = exception2.getMessage();
        assertTrue(actualMessage2.contains(expectedMessage2));
    }
}