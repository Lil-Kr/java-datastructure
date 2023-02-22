package com.cy.linkedlist.linkedlistclass;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}