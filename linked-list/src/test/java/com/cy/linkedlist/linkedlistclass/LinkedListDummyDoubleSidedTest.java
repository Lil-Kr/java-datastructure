package com.cy.linkedlist.linkedlistclass;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class LinkedListDummyDoubleSidedTest {

    @Test
    void add_1() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        log.info(list.toString());

        list.add(2,888);
        log.info(list.toString());
    }

    @Test
    void add_2() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.add(4,888);
        log.info(list.toString());
    }

    @Test
    void add_3() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.add(1,888);
        log.info(list.toString());
    }

    @Test
    void addFirst() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);
        list.addFirst(6);
        list.addFirst(7);
        log.info(list.toString());
    }

    @Test
    void addLast() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        log.info(list.toString());
    }

    @Test
    void getFirst() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        System.out.println(list.getFirst().toString());
    }

    @Test
    void getLast() {
        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        System.out.println(list.getLast().toString());
    }

    @Test
    void remove_success_1() {


    }

    @Test
    void removeFirst_success_1() {
        // 期望值
        Object[] expect1 = {2};
        Object expect2 = 2;
        Object[] expect3 = {};

        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        Object[] actual1 = list.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        Object actual2 = list.removeFirst();
        Assertions.assertEquals(expect2,actual2);

        Object[] actual3 = list.getArray();
        Assertions.assertArrayEquals(expect3,actual3);
    }

    @Test
    void removeFirst_success_2() {
        // 期望值
        Object[] expect1 = {2, 3, 4, 5, 6, 7};
        Object[] expect2 = {3, 4, 5, 6, 7};
        Object expect3 = 2;

        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        Object[] actual1 = list.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        Object actual3 = list.removeFirst();
        Assertions.assertEquals(expect3,actual3);

        Object[] actual2 = list.getArray();
        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2,actual2);
    }

    @Test
    void removeLast() {
        // 期望值
        Object[] expect1 = {2,3,4,5,6,7};
        Object[] expect2 = {2,3,4,5,6};
        Object expect3 = 7;

        LinkedListDummyDoubleSided list = new LinkedListDummyDoubleSided();
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        Object[] actual1 = list.getArray();

        log.info("expect1 value: {}", JSON.toJSONString(expect1));
        log.info("actual1 value: {}", JSON.toJSONString(actual1));
        Assertions.assertArrayEquals(expect1,actual1);

        Object actual3 = list.removeLast();
        Assertions.assertEquals(expect3,actual3);

        Object[] actual2 = list.getArray();
        log.info("expect2 value: {}", JSON.toJSONString(expect2));
        log.info("actual2 value: {}", JSON.toJSONString(actual2));
        Assertions.assertArrayEquals(expect2,actual2);
    }

}